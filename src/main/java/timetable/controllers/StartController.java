package timetable.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import timetable.models.LoginConstructor;

import javax.swing.*;
import java.io.IOException;

public class StartController {
    @FXML
    private ImageView image;
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginBtn;

    private LoginConstructor adminAccount;

    @FXML
    public  void initialize() {
        image.setImage(new Image("/images/Welcome to Garment Application.png"));
    }

    public StartController() throws IOException {
        this.adminAccount = new LoginConstructor("Admin","1234");
    }


    public void handleLoginBtnOnAction (ActionEvent event) throws IOException {
        if (adminAccount.checkPin(usernameTextField.getText(),passwordField.getText())) {
            try {
                FXRouter.goTo("home");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า home ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
        else{
//            String message = "Username or Password is Wrong!" +'\n' + "Please try again.";
//            JOptionPane.showMessageDialog(null,message);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username or Password are incorrect");
            alert.setContentText("Please try again.");
            alert.showAndWait();
            usernameTextField.clear();
            passwordField.clear();
        }
    }

//    @FXML
//    public void handleNextToHomeButton(ActionEvent actionEvent) {
//        try {
//            FXRouter.goTo("home");
//        } catch (IOException e) {
//            System.err.println("ไปที่หน้า home ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
//        }
//    }
}

