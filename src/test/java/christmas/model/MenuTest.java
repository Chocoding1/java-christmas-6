package christmas.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @ParameterizedTest
    @ValueSource(strings = {"장난감", "신발"})
    @DisplayName("존재하지 않는 메뉴를 입력할 경우 에외 발생")
    void menu_not_found(String menu) {
        //when & then
        assertThatThrownBy(() -> Menu.from(menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}