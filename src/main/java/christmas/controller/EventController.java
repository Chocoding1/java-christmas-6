package christmas.controller;

import christmas.model.EventBenefits;
import christmas.model.Order;
import christmas.model.OrderParser;
import christmas.model.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    private final OutputView outputView;
    private final InputView inputView;
    private final OrderParser orderParser;

    public EventController(OutputView outputView, InputView inputView, OrderParser orderParser) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.orderParser = orderParser;
    }

    public void run() {
        outputView.printWelcome();
        int date = inputView.readDate();
        VisitingDate visitingDate = new VisitingDate(date);
        String input = inputView.readOrder();
        Order order = orderParser.parse(input);
        outputView.printMenu(order);
        outputView.printTotalPrice(order);
        EventBenefits eventBenefits = new EventBenefits(visitingDate, order);
        outputView.printFreeGift(eventBenefits);
        outputView.printBenefitDetails(eventBenefits);
        outputView.printTotalBenefitAmount(eventBenefits);
        outputView.printExpectedPayAmount(eventBenefits);
        outputView.printEventBadge(eventBenefits);
    }
}
