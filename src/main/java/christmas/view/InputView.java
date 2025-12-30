package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String VISITING_DATE_INPUT_NOTICE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해주세요!)";

    private static final String ERROR_NOT_INTEGER = "[ERROR] 정수로 입력해주세요.";

    public int readDate() {
        System.out.println(VISITING_DATE_INPUT_NOTICE);
        String input = Console.readLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_INTEGER);
        }
    }
}
