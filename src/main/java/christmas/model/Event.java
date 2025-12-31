package christmas.model;

public enum Event {

    D_DAY("크리스마스 디데이 할인", 1000),
    WEEKDAYS("평일 할인", 2023),
    WEEKENDS("주말 할인", 2023),
    SPECIAL("특별 할인", 1000),
    FREE_GIFT("증정 이벤트", 25000),
    ;

    private final String name;
    private final int discountAmount;

    Event(String name, int discountAmount) {
        this.name = name;
        this.discountAmount = discountAmount;
    }

    public String getName() {
        return name;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
