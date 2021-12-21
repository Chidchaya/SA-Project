package timetable.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import timetable.models.CustomerConstructor;
import timetable.models.DetailGeneralBasic;
//import timetable.models.WorkList;
import timetable.models.WorkList;
import timetable.services.CategoryReadWrite;
import timetable.services.CustomerReadWrite;
import timetable.services.DataSource;
import timetable.services.GeneralReadWrite;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class GeneralBasicController {
    @FXML
    TextField title, size;
    @FXML
    ChoiceBox<String> priority, category, smlxl, idChoiceBox;
    @FXML
    ChoiceBox<String> stHour, stMin, fiHour, fiMin;
    @FXML
    Button save, back;
    @FXML
    Label statusLabel, alreadyLabel, sendStatusLabel;
    @FXML
    DatePicker datePicker, sendDatePicker;
//    @FXML
//    CheckBox generalCheckBox;

    private DetailGeneralBasic generalBasicTask;
    private WorkList workList, categoryList, customerList;
    private DataSource dataSource, categorySource, customerSource;

    @FXML
    private ImageView background;

    @FXML
    public void initialize() {
        background.setImage(new Image("/images/Background 2.png"));
        Platform.runLater((Runnable) new Runnable() {
            @Override
            public void run() {
                dataSource = new GeneralReadWrite("data", "general.csv");
                workList = dataSource.getData();
                title.setDisable(true);
                sendDatePicker.setDisable(true);
                categorySource = new CategoryReadWrite("data", "category.csv");
                categoryList = categorySource.getData();
                customerSource = new CustomerReadWrite("data","customer.csv");
                customerList = customerSource.getData();
                for (String customerConstructor : customerList.getCustomerIdString()) {
                    idChoiceBox.getItems().add(customerConstructor);
                }
                for (int i = 0; i < categoryList.toListCategory().size(); i++) {
                    category.getItems().add(categoryList.toListCategory().get(i).getNameCategory());
                }
                category.getItems().add("***Don't have***");
                priority.getItems().addAll("Design", "Repair");
                smlxl.getItems().addAll("xs", "s", "m", "l", "xl", "xxl");
                for (int i = 0; i <= 23; i++) {
                    if (i < 10) {
                        stHour.getItems().addAll("0" + i);
                        fiHour.getItems().addAll("0" + i);
                    } else {
                        stHour.getItems().addAll(String.valueOf(i));
                        fiHour.getItems().addAll(String.valueOf(i));
                    }
                }
                for (int i = 0; i <= 59; i++) {
                    if (i < 10) {
                        stMin.getItems().addAll("0" + i);
                        fiMin.getItems().addAll("0" + i);
                    } else {
                        stMin.getItems().addAll(String.valueOf(i));
                        fiMin.getItems().addAll(String.valueOf(i));
                    }
                }
//                System.out.println(categoryList.toListCategory().toString());
            }
        });
    }

    @FXML
    public void handleSaveAction(ActionEvent event) throws IOException {
//        System.out.println(datePicker.getValue());
        //System.out.println("Error");
//        if (generalCheckBox.isSelected() == false
//                && title.getText().equals("") && datePicker.getValue() == null
//                && stHour.getValue() == null && stMin.getValue() == null && fiHour.getValue() == null
//                && fiMin.getValue() == null && priority.getValue() == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please fill out all information.");
//            alert.showAndWait();
//        } else
        if (!(workList.checkNameGeneral(title.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ชือนี้มีซ้ำแล้ว");
            alert.setContentText("กรุณาใช้ชื่อใหม่");
            alert.showAndWait();
//        } else if (generalCheckBox.isSelected() == false) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please select the work.");
//            alert.showAndWait();
        }
//        else if (title.getText().equals("")) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please check the information");
//            alert.showAndWait();
//            System.out.println("1");
//        }
        else if (datePicker.getValue() == null) {
            //System.out.println("Error");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check the information");
            alert.showAndWait();
            System.out.println("2");
        }  else if (stHour.getValue() == null && stMin.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check the information");
            alert.showAndWait();
            System.out.println("3");
        } else if (fiHour.getValue() == null && fiMin.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check the information");
            alert.showAndWait();
            System.out.println("4");
        }
//        else if (sendDatePicker.getValue().isBefore(datePicker.getValue())) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("The delivery date can't be less than the cut-off date. Please enter the new date.");
//            alert.showAndWait();
//        }
        else if (Integer.parseInt(stHour.getValue()) == Integer.parseInt(fiHour.getValue()) &&
                (Integer.parseInt(stMin.getValue()) > Integer.parseInt(fiMin.getValue()))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Incomplete information");
                alert.setContentText("Please check the information");
                alert.show();
            System.out.println("5");
        } else if (Integer.parseInt(stHour.getValue()) > Integer.parseInt(fiHour.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check the information");
            alert.show();
            System.out.println("6");
        } else if (Integer.parseInt(stHour.getValue()) == Integer.parseInt(fiHour.getValue()) &&
                Integer.parseInt(stMin.getValue()) == Integer.parseInt(fiMin.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check the information");
            alert.show();
            System.out.println("7");
        } else if (priority.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check the information");
            alert.showAndWait();
            System.out.println("8");
        } else if (category.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check the information");
            alert.showAndWait();
            System.out.println("9");
        } else if (smlxl.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check the information");
            alert.showAndWait();
            System.out.println("10");
        }
        else {
            if (!(title.getText().equals("")) && datePicker.getValue() != null && stHour.getValue() != null
                    && stMin.getValue() != null && fiHour.getValue() != null &&
                    fiMin.getValue() != null && priority.getValue() != null &&
                    category.getValue() != null && smlxl.getValue() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMATION");
                alert.setHeaderText("Please check the information is correct!?");
                alert.showAndWait();
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("INFORMATION");
                alert1.setHeaderText("It has been done (already)");
                alert1.show();
            }
//            if (sendDatePicker.getValue() == null) {
//                generalBasicTask = new DetailGeneralBasic(String.valueOf(workList.runIdOrder()),
//                        datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                        stHour.getValue() + ":" + stMin.getValue(),
//                        fiHour.getValue() + ":" + fiMin.getValue(),
//                        priority.getValue(), "Not started", category.getValue(), size.getText(), smlxl.getValue(),
//                        "Not delivery yet", datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            }
            else {
                generalBasicTask = new DetailGeneralBasic(String.valueOf(workList.runIdOrder()),
                        datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        stHour.getValue() + ":" + stMin.getValue(),
                        fiHour.getValue() + ":" + fiMin.getValue(),
                        priority.getValue(), "Not started", category.getValue(), idChoiceBox.getValue(),
                        smlxl.getValue(), "Not delivery yet",
                        datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMATION");
                alert.setHeaderText("Please check the information is correct!?");
                alert.showAndWait();
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("INFORMATION");
                alert1.setHeaderText("It has been done (already)");
                alert1.show();
            }


//            System.out.println(generalBasicTask.toString());
            title.clear();
            datePicker.setValue(null);
            stHour.setValue(null);
            stMin.setValue(null);
            fiHour.setValue(null);
            fiMin.setValue(null);
            priority.setValue(null);
            category.setValue(null);
            idChoiceBox.setValue(null);
            smlxl.setValue(null);
            sendDatePicker.setValue(null);
            workList.addGeneral(generalBasicTask);
            dataSource.setData(workList);

        }
    }

    @FXML
    public void handleBackGeneralJobToHomeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}