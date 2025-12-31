package christmas.view;

import christmas.model.BenefitDetails;
import christmas.model.Event;
import christmas.model.EventBadge;
import christmas.model.EventBenefits;
import christmas.model.Menu;
import christmas.model.Order;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static final String WELCOME_NOTICE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_NOTICE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_TITLE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_TITLE = "<할인 전 총주문 금액>";
    private static final String FREE_GIFT_TITLE = "<증정 메뉴>";
    private static final String BENEFIT_DETAILS_TITLE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT_TITLE = "<총혜택 금액>";
    private static final String EXPECTED_PAY_AMOUNT_TITLE = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_TITLE = "<12월 이벤트 배지>";

    private static final String FREE_GIFT_NOTICE = "샴페인 1개";
    private static final String NON_EVENT_NOTICE = "없음";

    private final DecimalFormat df = new DecimalFormat("###,###,###");

    public void printWelcome() {
        System.out.println(WELCOME_NOTICE);
    }

    public void printPreviewTitle() {
        System.out.println(PREVIEW_NOTICE);
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
        System.out.println(df.format(order.totalPrice()) + "원");
    }

    public void printFreeGift(EventBenefits eventBenefits) {
        System.out.println(FREE_GIFT_TITLE);
        if (eventBenefits.isFreeGift()) {
            System.out.println(FREE_GIFT_NOTICE);
            return;
        }

        System.out.println(NON_EVENT_NOTICE);
    }

    public void printBenefitDetails(EventBenefits eventBenefits) {
        System.out.println(BENEFIT_DETAILS_TITLE);
        BenefitDetails details = eventBenefits.getBenefitDetails();

        if (details.isNull()) {
            System.out.println(NON_EVENT_NOTICE);
            return;
        }

        Map<Event, Integer> detailsMap = details.getDetails();
        for (Event event : detailsMap.keySet()) {
            System.out.println(event.getName() + ": -" + df.format(detailsMap.get(event)) + "원");
        }
    }

    public void printTotalBenefitAmount(EventBenefits eventBenefits) {
        System.out.println(TOTAL_BENEFIT_AMOUNT_TITLE);
        System.out.println("-" + df.format(eventBenefits.getTotalBenefitAmount()) + "원");
    }

    public void printExpectedPayAmount(EventBenefits eventBenefits) {
        System.out.println(EXPECTED_PAY_AMOUNT_TITLE);
        System.out.println(df.format(eventBenefits.getExpectedPayAmount()) + "원");
    }

    public void printEventBadge(EventBenefits eventBenefits) {
        System.out.println(EVENT_BADGE_TITLE);
        System.out.println(EventBadge.from(eventBenefits.getTotalBenefitAmount()).getName());
    }
}
