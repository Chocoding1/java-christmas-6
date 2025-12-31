package christmas.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitingDateTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    @DisplayName("유효하지 않은 날짜를 입력할 경우 예외 발생")
    void create_visitingDate_fail_when_invalid_date(int date) {
        //when & then
        assertThatThrownBy(() -> new VisitingDate(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}