package christmas.config;

import christmas.controller.EventController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class AppConfig {

    private EventController eventController;
    private OutputView outputView;
    private InputView inputView;

    public EventController eventController() {
        if (eventController == null) {
            eventController = new EventController(outputView(), inputView());
        }
        return eventController;
    }

    private OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    private InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }
}
