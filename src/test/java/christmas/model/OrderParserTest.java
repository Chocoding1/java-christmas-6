package christmas.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드*1,티본스테이크/1", "시저샐러드-1/티본스테이크-1"})
    @DisplayName("메뉴 형식이 예시와 다른 경우 예외 발생")
    void parse_fail_when_invalid_orderInput(String orderInput) {
        //given
        OrderParser orderParser = new OrderParser();

        //when & then
        assertThatThrownBy(() -> orderParser.parse(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("주문 수량이 정수 타입이 아닌 경우 예외 발생")
    void parse_fail_when_orderAmount_not_integer() {
        //given
        String orderInput = "시저샐러드-one,티본스테이크-two";
        OrderParser orderParser = new OrderParser();

        //when & then
        assertThatThrownBy(() -> orderParser.parse(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("주문 수량이 1보다 작은 경우 예외 발생")
    void parse_fail_when_orderAmount_less_than_one() {
        //given
        String orderInput = "시저샐러드-0,티본스테이크-0";
        OrderParser orderParser = new OrderParser();

        //when & then
        assertThatThrownBy(() -> orderParser.parse(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드-1,시저샐러드-1", "시저샐러드-1,시저샐러드-3"})
    @DisplayName("중복 메뉴를 입력한 경우 예외 발생")
    void parse_fail_when_duplicate_orderInput(String orderInput) {
        //given
        OrderParser orderParser = new OrderParser();

        //when & then
        assertThatThrownBy(() -> orderParser.parse(orderInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}