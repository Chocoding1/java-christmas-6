package christmas.model;

public enum EventBadge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0),
    ;

    private final String name;
    private final int conditionAmount;

    EventBadge(String name, int conditionAmount) {
        this.name = name;
        this.conditionAmount = conditionAmount;
    }

    public String getName() {
        return name;
    }

    public static EventBadge from(int benefitAmount) {
        for (EventBadge eventBadge : EventBadge.values()) {
            if (eventBadge.conditionAmount <= benefitAmount) {
                return eventBadge;
            }
        }
        return NONE;
    }
}
