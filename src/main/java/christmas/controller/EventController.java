package christmas.controller;

import christmas.view.OutputView;

public class EventController {

    private final OutputView outputView;

    public EventController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcome();
    }
}
