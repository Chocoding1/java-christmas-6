package christmas.controller;

import christmas.model.VisitingDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    private final OutputView outputView;
    private final InputView inputView;

    public EventController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printWelcome();
        int date = inputView.readDate();
        VisitingDate visitingDate = new VisitingDate(date);
    }
}
