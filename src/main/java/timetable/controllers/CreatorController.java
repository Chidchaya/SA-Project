package timetable.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CreatorController {
    @FXML
    private ImageView image, image1, image2, background;

    @FXML
    public  void initialize() {
        image.setImage(new Image("/images/your_image.jpg"));
        image1.setImage(new Image("/images/ying_image.jpg"));
        image2.setImage(new Image("/images/im_image.png"));
        background.setImage(new Image("/images/Background 6.png"));
    }

    @FXML
    public void handleBackCreatorToHomeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}

