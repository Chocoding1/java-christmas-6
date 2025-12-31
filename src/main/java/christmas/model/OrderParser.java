package christmas.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OrderParser {

    private static final String COMMA = ",";
    private static final String HYPHEN = "-";

    private static final String ERROR_INVALID_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public Order parse(String input) {
        List<String> orderTokens = splitByComma(input);
        return parserOrder(orderTokens);
    }

    private List<String> splitByComma(String input) {
        return Arrays.stream(input.split(COMMA))
                .toList();
    }

    private Order parserOrder(List<String> orderTokens) {
        EnumMap<Menu, Integer> order = new EnumMap<>(Menu.class);
        for (String orderToken : orderTokens) {
            List<String> menuToken = splitByHyphen(orderToken);
            validateFormat(menuToken);
            Menu menu = findMenu(menuToken);
            int amount = getAmount(menuToken);
            validateDuplicate(order, menu);
            order.put(menu, amount);
        }

        return new Order(order);
    }

    private List<String> splitByHyphen(String orderToken) {
        return Arrays.stream(orderToken.split(HYPHEN)).toList();
    }

    private void validateFormat(List<String> menuToken) {
        if (menuToken.size() != 2) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER);
        }
    }

    private Menu findMenu(List<String> menuToken) {
        return Menu.from(menuToken.get(0));
    }

    private int getAmount(List<String> menuToken) {
        String initialAmount = menuToken.get(1);

        int amount = convertToInt(initialAmount);
        validateRange(amount);

        return amount;
    }

    private int convertToInt(String initialAmount) {
        try {
            return Integer.parseInt(initialAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER);
        }
    }

    private void validateRange(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER);
        }
    }

    private void validateDuplicate(Map<Menu, Integer> order, Menu menu) {
        if (order.containsKey(menu)) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER);
        }
    }
}
