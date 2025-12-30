package christmas.config;

import christmas.controller.EventController;
import christmas.view.OutputView;

public class AppConfig {

    private EventController eventController;
    private OutputView outputView;

    public EventController eventController() {
        if (eventController == null) {
            eventController = new EventController(outputView());
        }
        return eventController;
    }

    private OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }
}
