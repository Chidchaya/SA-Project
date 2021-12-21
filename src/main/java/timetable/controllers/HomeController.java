package timetable.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import timetable.services.PDFFileDataSource;

import java.io.IOException;
import java.util.Optional;

public class HomeController {

    @FXML Button creatorButton, information, customerBtn, allList, checkStock;
    @FXML Button generalJobButton, generalListButton;

    @FXML
    private ImageView background;

    @FXML
    public  void initialize() {
        background.setImage(new Image("/images/Background 1.png"));
    }

    @FXML
    private void handlePDFButton() throws IOException {
        PDFFileDataSource pdfFileDataSource = new PDFFileDataSource("PDF File", "6210450067.pdf");
        pdfFileDataSource.openPDFFile();
    }

    @FXML
    public void handleToGeneralJobButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("general");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า General Job ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleHomeToGeneralListButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("general List");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า General List ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToHomeToWeeklyJobButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("weekly");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Weekly Job ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleHomeToWeeklyListButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("weekly List");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Weekly List ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToCreatorButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("creator");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Creator ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleAllListButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("all List");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า All List ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleBackToStartButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("Do you want to log out of the program?");
//        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                FXRouter.goTo("start");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า Start ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        } else {
            try {
                FXRouter.goTo("home");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า Home ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    @FXML
    public void handleHomeToCategoryButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("category");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Category ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleHomeToCheckStockController(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("check Stock");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า CheckStockController ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleHomeToCustomerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("customer");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Customer ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}

