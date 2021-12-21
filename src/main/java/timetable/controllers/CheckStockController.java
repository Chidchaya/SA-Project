package timetable.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import timetable.models.StockConstructor;
import timetable.models.CheckStock;
import timetable.services.CheckStockReadWrite;

import java.io.IOException;

public class CheckStockController {
    @FXML
    Button selectFabricBtn, selectElasticBtn, selectThreadNeedleBtn, selectPlanBtn, selectButtonsBtn,
            addFabricBtn, addElasticBtn, addThreadNeedleBtn, addPlanBtn, addButtonsBtn, nextBtn;
    @FXML
    Label FabricLabel, ElasticLabel, ThreadNeedleLabel, PlanLabel, ButtonsLabel;

    @FXML
    private ImageView background;

    private CheckStock checkStock;
    private CheckStockReadWrite checkStockReadWrite;

    @FXML
    public void initialize() {
        background.setImage(new Image("/images/Background 2.png"));
        Platform.runLater((Runnable) new Runnable() {
            @Override
            public void run() {
                checkStockReadWrite = new CheckStockReadWrite("data", "stock.csv");
                checkStock = checkStockReadWrite.getData();
                FabricLabel.setText("" + checkStock.showStock("001"));
                ElasticLabel.setText("" + checkStock.showStock("002"));
                ThreadNeedleLabel.setText("" + checkStock.showStock("003"));
                PlanLabel.setText("" + checkStock.showStock("004"));
                ButtonsLabel.setText("" + checkStock.showStock("005"));
            }
        });
    }

    @FXML
    public void handleSelectFabricBtnOnAction(ActionEvent event) throws IOException {
        //Change here
        if(checkStock.checkPin("001")){
            if(checkStock.withdraw(1)){
                FabricLabel.setText("" + checkStock.showStock("001"));
                checkStockReadWrite.setData(checkStock);
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Out of Stock");
                alert.setContentText("Please add device.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void handleSelectElasticBtnOnAction(ActionEvent event) throws IOException {
        //Change here
        if(checkStock.checkPin("002")){
            if(checkStock.withdraw(1)){
                ElasticLabel.setText("" + checkStock.showStock("002"));
                checkStockReadWrite.setData(checkStock);
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Out of Stock");
                alert.setContentText("Please add device.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void handleSelectThreadNeedleBtnOnAction(ActionEvent event) throws IOException {
        //Change here
        if(checkStock.checkPin("003")){
            if(checkStock.withdraw(1)){
                ThreadNeedleLabel.setText("" + checkStock.showStock("003"));
                checkStockReadWrite.setData(checkStock);
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Out of Stock");
                alert.setContentText("Please add device.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void handleSelectPlanBtnOnAction(ActionEvent event) throws IOException {
        //Change here
        if(checkStock.checkPin("004")){
            if(checkStock.withdraw(1)){
                PlanLabel.setText("" + checkStock.showStock("004"));
                checkStockReadWrite.setData(checkStock);
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Out of Stock");
                alert.setContentText("Please add device.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void handleSelectButtonsBtnOnAction(ActionEvent event) throws IOException {
        //Change here
        if(checkStock.checkPin("005")){
            if(checkStock.withdraw(1)){
                ButtonsLabel.setText("" + checkStock.showStock("005"));
                checkStockReadWrite.setData(checkStock);
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Out of Stock");
                alert.setContentText("Please add device.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void handleAddFabricBtnOnAction(ActionEvent event) throws IOException {
        //Change here
        if(checkStock.checkPin("001")){
            if(checkStock.deposit(1)){
                FabricLabel.setText("" + checkStock.showStock("001"));
                checkStockReadWrite.setData(checkStock);
            }
        }
    }

    @FXML
    public void handleAddElasticBtnOnAction(ActionEvent event) throws IOException {
        //Change here
        if(checkStock.checkPin("002")){
            if(checkStock.deposit(1)){
                ElasticLabel.setText("" + checkStock.showStock("002"));
                checkStockReadWrite.setData(checkStock);
            }
        }
    }

    @FXML
    public void handleAddThreadNeedleBtnOnAction(ActionEvent event) throws IOException {
        //Change here
        if(checkStock.checkPin("003")){
            if(checkStock.deposit(1)){
                ThreadNeedleLabel.setText("" + checkStock.showStock("003"));
                checkStockReadWrite.setData(checkStock);
            }
        }
    }

    @FXML
    public void handleAddPlanBtnOnAction(ActionEvent event) throws IOException {
        //Change here
        if(checkStock.checkPin("004")){
            if(checkStock.deposit(1)){
                PlanLabel.setText("" + checkStock.showStock("004"));
                checkStockReadWrite.setData(checkStock);
            }
        }
    }

    @FXML
    public void handleAddButtonsBtnOnAction(ActionEvent event) throws IOException {
        //Change here
        if(checkStock.checkPin("005")){
            if(checkStock.deposit(1)){
                ButtonsLabel.setText("" + checkStock.showStock("005"));
                checkStockReadWrite.setData(checkStock);
            }
        }
    }

    @FXML
    public void handleNextCheckStockControllerToHomeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
