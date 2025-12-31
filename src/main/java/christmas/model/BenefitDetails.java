package christmas.model;

import java.util.EnumMap;
import java.util.Map;

public class BenefitDetails {

    private final EnumMap<Event, Integer> details;

    public BenefitDetails(EnumMap<Event, Integer> details) {
        this.details = details;
    }

    public Map<Event, Integer> getDetails() {
        return Map.copyOf(details);
    }

    public boolean isNull() {
        return details.isEmpty();
    }
}
