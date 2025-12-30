package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitingDate {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int START_DATE = 1;

    private static final int START_DISCOUNT_AMOUNT = 1000;
    private static final int INCREMENT_AMOUNT = 100;

    private static final String ERROR_OUT_OF_RANGE_DATE = "[ERROR] 방문 날짜는 1 ~ 31 사이의 숫자로만 입력해주세요.";

    private final int date;
    private final int dDayDiscountAmount;
    private boolean weekDaysDiscount;
    private boolean weekendsDiscount;
    private boolean specialDiscount;

    public VisitingDate(int date) {
        validateRange(date);
        this.date = date;
        this.dDayDiscountAmount = calculatedDayDiscountAmount(date);
    }

    private void validateRange(int date) {
        if (date < 1 || 31 < date) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE_DATE);
        }
    }

    private int calculatedDayDiscountAmount(int date) {
        if (date > 25) {
            return 0;
        }

        return (date - START_DATE) * INCREMENT_AMOUNT + START_DISCOUNT_AMOUNT;
    }
}
