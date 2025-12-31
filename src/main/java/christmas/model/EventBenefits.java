package christmas.model;

import java.util.EnumMap;
import java.util.Map;

public class EventBenefits {

    private static final int FREE_GIFT_CONDITION_PRICE = 120_000;

    private final VisitingDate visitingDate;
    private final Order order;
    private final boolean freeGift;

    public EventBenefits(VisitingDate visitingDate, Order order) {
        this.visitingDate = visitingDate;
        this.order = order;
        freeGift = canFreeGift(order);
    }

    public boolean isFreeGift() {
        return freeGift;
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
            details.put(Event.D_DAY, visitingDate.getDDayDiscountAmount());
        }
    }

    private void addDayOfWeekDetails(EnumMap<Event, Integer> details) {
        Map<Menu, Integer> orderInfo = order.getOrder();

        if (visitingDate.isWeekendsDiscount()) {
            for (Menu menu : orderInfo.keySet()) {
                if (MenuGroup.findByMenu(menu) == MenuGroup.MAIN) {
                    details.put(Event.WEEKENDS, details.getOrDefault(Event.WEEKENDS, 0) + orderInfo.get(menu) * Event.WEEKENDS.getDiscountAmount());
                }
            }
            return;
        }

        for (Menu menu : orderInfo.keySet()) {
            if (MenuGroup.findByMenu(menu) == MenuGroup.DESSERT) {
                details.put(Event.WEEKDAYS, details.getOrDefault(Event.WEEKDAYS, 0) + orderInfo.get(menu) * Event.WEEKDAYS.getDiscountAmount());
            }
        }

    }

    private void addSpecialDetails(EnumMap<Event, Integer> details) {
        if (visitingDate.isSpecialDiscount()) {
            details.put(Event.SPECIAL, Event.SPECIAL.getDiscountAmount());
        }
    }

    private void addFreeGiftDetails(EnumMap<Event, Integer> details) {
        if (freeGift) {
            details.put(Event.FREE_GIFT, Event.FREE_GIFT.getDiscountAmount());
        }
    }

    private boolean canFreeGift(Order order) {
        return order.totalPrice() >= FREE_GIFT_CONDITION_PRICE;
    }
}
