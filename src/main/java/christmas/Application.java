package christmas;

import christmas.config.AppConfig;
import christmas.controller.EventController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        EventController eventController = appConfig.eventController();
        eventController.run();
    }
}
