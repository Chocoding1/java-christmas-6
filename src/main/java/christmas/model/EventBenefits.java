package christmas.model;

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

    private boolean canFreeGift(Order order) {
        return order.totalPrice() >= FREE_GIFT_CONDITION_PRICE;
    }
}
