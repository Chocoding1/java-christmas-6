package christmas.model;

import java.util.EnumMap;
import java.util.Map;

public class EventBenefits {

    private static final int FREE_GIFT_CONDITION_PRICE = 120_000;

    private final VisitingDate visitingDate;
    private final Order order;
    private final boolean freeGift;
    private int totalBenefitPrice;

    public EventBenefits(VisitingDate visitingDate, Order order) {
        this.visitingDate = visitingDate;
        this.order = order;
        this.freeGift = canFreeGift(order);
        this.totalBenefitPrice = 0;
    }

    public boolean isFreeGift() {
        return freeGift;
    }

    public int getTotalBenefitPrice() {
        return totalBenefitPrice;
    }

    public int getExpectedPayAmount() {
        int totalPrice = order.totalPrice();
        if (freeGift) {
            return totalPrice - (totalBenefitPrice - Event.FREE_GIFT.getDiscountAmount());
        }
        return totalPrice - totalBenefitPrice;
    }

    public BenefitDetails getBenefitDetails() {
        EnumMap<Event, Integer> details = new EnumMap<>(Event.class);
        addDDayDetails(details);
        addDayOfWeekDetails(details);
        addSpecialDetails(details);
        addFreeGiftDetails(details);

        return new BenefitDetails(details);
    }

    private void addDDayDetails(EnumMap<Event, Integer> details) {
        if (visitingDate.getDDayDiscountAmount() > 0) {
            int benefitPrice = visitingDate.getDDayDiscountAmount();
            details.put(Event.D_DAY, benefitPrice);
            totalBenefitPrice += benefitPrice;
        }
    }

    private void addDayOfWeekDetails(EnumMap<Event, Integer> details) {
        Map<Menu, Integer> orderInfo = order.getOrder();

        if (visitingDate.isWeekendsDiscount()) {
            for (Menu menu : orderInfo.keySet()) {
                if (MenuGroup.findByMenu(menu) == MenuGroup.MAIN) {
                    int benefitPrice = orderInfo.get(menu) * Event.WEEKENDS.getDiscountAmount();
                    details.put(Event.WEEKENDS, details.getOrDefault(Event.WEEKENDS, 0) + benefitPrice);
                    totalBenefitPrice += benefitPrice;
                }
            }
            return;
        }

        for (Menu menu : orderInfo.keySet()) {
            if (MenuGroup.findByMenu(menu) == MenuGroup.DESSERT) {
                int benefitPrice = orderInfo.get(menu) * Event.WEEKDAYS.getDiscountAmount();
                details.put(Event.WEEKDAYS, details.getOrDefault(Event.WEEKDAYS, 0) + benefitPrice);
                totalBenefitPrice += benefitPrice;
            }
        }

    }

    private void addSpecialDetails(EnumMap<Event, Integer> details) {
        if (visitingDate.isSpecialDiscount()) {
            int benefitPrice = Event.SPECIAL.getDiscountAmount();
            details.put(Event.SPECIAL, benefitPrice);
            totalBenefitPrice += benefitPrice;
        }
    }

    private void addFreeGiftDetails(EnumMap<Event, Integer> details) {
        if (freeGift) {
            int benefitPrice = Event.FREE_GIFT.getDiscountAmount();
            details.put(Event.FREE_GIFT, benefitPrice);
            totalBenefitPrice += benefitPrice;
        }
    }

    private boolean canFreeGift(Order order) {
        return order.totalPrice() >= FREE_GIFT_CONDITION_PRICE;
    }
}
