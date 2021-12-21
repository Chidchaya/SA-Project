package timetable;

import com.github.saacsos.FXRouter;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXRouter.bind(this, primaryStage, "6210450067", 900, 600);

        configRoute();

        FXRouter.goTo("start");
    }

    private static void configRoute() {
        FXRouter.when("home", "home.fxml");
        FXRouter.when("creator", "creator.fxml");
        FXRouter.when("general", "general.fxml");
        FXRouter.when("weekly","weekly.fxml");
        FXRouter.when("forward", "forward.fxml");
        FXRouter.when("project", "project.fxml");
        FXRouter.when("general List", "general List.fxml");
        FXRouter.when("weekly List", "weekly List.fxml");
        FXRouter.when("forward List", "forward List.fxml");
        FXRouter.when("project List", "project List.fxml");
        FXRouter.when("start", "start.fxml");
        FXRouter.when("all List", "all List.fxml");
        FXRouter.when("category", "category.fxml");
        FXRouter.when("check Stock", "check Stock.fxml");
        FXRouter.when("customer", "customer.fxml");

    }


    public static void main(String[] args) {
        launch(args);
    }
}

