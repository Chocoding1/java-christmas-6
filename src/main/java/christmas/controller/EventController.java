package christmas.controller;

import static christmas.handler.ExceptionHandler.*;

import christmas.handler.ExceptionHandler;
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
        VisitingDate visitingDate = retryUtilSuccess(this::getVisitingDate);
        Order order = retryUtilSuccess(this::getOrder);
        EventBenefits eventBenefits = new EventBenefits(visitingDate, order);
        printBenefitsPreview(order, eventBenefits);
    }

    private Order getOrder() {
        String input = inputView.readOrder();
        return orderParser.parse(input);
    }

    private VisitingDate getVisitingDate() {
        int date = inputView.readDate();
        return new VisitingDate(date);
    }

    private void printBenefitsPreview(Order order, EventBenefits eventBenefits) {
        outputView.printPreviewTitle();
        outputView.printMenu(order);
        outputView.printTotalPrice(order);
        outputView.printFreeGift(eventBenefits);
        outputView.printBenefitDetails(eventBenefits);
        outputView.printTotalBenefitAmount(eventBenefits);
        outputView.printExpectedPayAmount(eventBenefits);
        outputView.printEventBadge(eventBenefits);
    }
}
