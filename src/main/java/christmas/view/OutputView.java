package christmas.view;

import christmas.model.BenefitDetails;
import christmas.model.Event;
import christmas.model.EventBenefits;
import christmas.model.Menu;
import christmas.model.Order;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static final String WELCOME_NOTICE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_TITLE = "<할인 전 총주문 금액>";
    private static final String FREE_GIFT_TITLE = "<증정 메뉴>";


    private static final String FREE_GIFT_NOTICE = "샴페인 1개";
    private static final String NON_FREE_GIFT_NOTICE = "없음";

    public void printWelcome() {
        System.out.println(WELCOME_NOTICE);
    }

    public void printMenu(Order order) {
        System.out.println(ORDER_MENU_TITLE);
        Map<Menu, Integer> orderInfo = order.getOrder();
        for (Menu menu : orderInfo.keySet()) {
            System.out.println(menu.getName() + " " + orderInfo.get(menu) + "개");
        }
        System.out.println();
    }

    public void printTotalPrice(Order order) {
        System.out.println(TOTAL_PRICE_TITLE);
        DecimalFormat df = new DecimalFormat("###,###,###");
        System.out.println(df.format(order.totalPrice()) + "원");
    }

    public void printFreeGift(EventBenefits eventBenefits) {
        System.out.println(FREE_GIFT_TITLE);
        if (eventBenefits.isFreeGift()) {
            System.out.println(FREE_GIFT_NOTICE);
            return;
        }

        System.out.println(NON_FREE_GIFT_NOTICE);
    }

    public void printBenefitDetails(EventBenefits eventBenefits) {
        BenefitDetails details = eventBenefits.getBenefitDetails();
        Map<Event, Integer> detailsMap = details.getDetails();
        DecimalFormat df = new DecimalFormat("###,###,###");

        for (Event event : detailsMap.keySet()) {
            System.out.println(event.getName() + ": -" + df.format(detailsMap.get(event)) + "원");
        }
    }
}
