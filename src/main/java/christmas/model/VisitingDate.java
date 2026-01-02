package christmas.model;

import static christmas.model.ErrorMessage.ERROR_INVALID_DATE;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitingDate {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int FIRST_DATE = 1;
    private static final int CHRISTMAS_DATE = 25;
    private static final int LAST_DATE = 31;
    private static final int FRIDAY_VALUE = 5;
    private static final int SATURDAY_VALUE = 6;
    private static final int SUNDAY_VALUE = 7;

    private static final int START_DISCOUNT_AMOUNT = 1000;
    private static final int INCREMENT_AMOUNT = 100;

    private final int date;
    private final int dDayDiscountAmount;
    private final boolean weekendsDiscount;
    private final boolean specialDiscount;

    public VisitingDate(int date) {
        validateRange(date);
        this.date = date;
        this.dDayDiscountAmount = calculatedDayDiscountAmount();
        this.weekendsDiscount = setWeekendsDiscount();
        this.specialDiscount = setSpecialDiscount();
    }

    public int getDDayDiscountAmount() {
        return dDayDiscountAmount;
    }

    public boolean isWeekendsDiscount() {
        return weekendsDiscount;
    }

    public boolean isSpecialDiscount() {
        return specialDiscount;
    }

    private void validateRange(int date) {
        if (date < FIRST_DATE || LAST_DATE < date) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE.getMessage());
        }
    }

    private int calculatedDayDiscountAmount() {
        if (date > CHRISTMAS_DATE) {
            return 0;
        }

        return (date - FIRST_DATE) * INCREMENT_AMOUNT + START_DISCOUNT_AMOUNT;
    }

    private boolean setWeekendsDiscount() {
        LocalDate localDate = LocalDate.of(YEAR, MONTH, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        return dayOfWeek.getValue() == FRIDAY_VALUE || dayOfWeek.getValue() == SATURDAY_VALUE;
    }

    private boolean setSpecialDiscount() {
        LocalDate localDate = LocalDate.of(YEAR, MONTH, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        return dayOfWeek.getValue() == SUNDAY_VALUE || date == CHRISTMAS_DATE;
    }
}
