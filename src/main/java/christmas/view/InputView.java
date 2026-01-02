package christmas.view;

import static christmas.model.ErrorMessage.ERROR_INVALID_DATE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String VISITING_DATE_INPUT_NOTICE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)";
    private static final String ORDER_MENU_INPUT_NOTICE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public int readDate() {
        System.out.println(VISITING_DATE_INPUT_NOTICE);
        String input = Console.readLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE.getMessage());
        }
    }

    public String readOrder() {
        System.out.println(ORDER_MENU_INPUT_NOTICE);
        return Console.readLine();
    }
}
