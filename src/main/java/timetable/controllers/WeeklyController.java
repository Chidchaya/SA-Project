//package timetable.controllers;
//
//import com.github.saacsos.FXRouter;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import timetable.models.DetailWeekly;
//import timetable.models.WorkList;
//import timetable.services.CategoryReadWrite;
//import timetable.services.DataSource;
//import timetable.services.WeeklyReadWrite;
//
//import java.io.IOException;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//
//public class WeeklyController {
//    @FXML
//    TextField title;
//    @FXML
//    ChoiceBox<String> priority, category;
//    @FXML
//    ChoiceBox<String> stHour, stMin, fiHour, fiMin;
//    @FXML
//    Button save, back;
//    @FXML
//    Label statusLabel, sendStatusLabel;
//    @FXML
//    DatePicker datePicker, sendDatePicker;
//
//
//    private DetailWeekly weeklyTask;
//    private WorkList workList, categoryList;
//    private DataSource dataSource, categorySource;
//
//    @FXML
//    private ImageView background;
//
//    @FXML
//    public void initialize() {
//        background.setImage(new Image("/images/Background 2.png"));
//        Platform.runLater((Runnable) new Runnable() {
//            @Override
//            public void run() {
//                dataSource = new WeeklyReadWrite("data", "weekly.csv");
//                workList = dataSource.getData();
//                categorySource = new CategoryReadWrite("data", "category.csv");
//                categoryList = categorySource.getData();
//                for (int i = 0; i < categoryList.toListCategory().size(); i++) {
//                    category.getItems().add(categoryList.toListCategory().get(i).getNameCategory());
//                }
//                category.getItems().add("***Don't have***");
//                priority.getItems().addAll("Stitch (ปัก)", "Sew (เย็บ)", "ปะ");
//                for (int i = 0; i <= 23; i++) {
//                    if (i < 10) {
//                        stHour.getItems().addAll("0" + i);
//                        fiHour.getItems().addAll("0" + i);
//                    } else {
//                        stHour.getItems().addAll(String.valueOf(i));
//                        fiHour.getItems().addAll(String.valueOf(i));
//                    }
//                }
//                for (int i = 0; i <= 59; i++) {
//                    if (i < 10) {
//                        stMin.getItems().addAll("0" + i);
//                        fiMin.getItems().addAll("0" + i);
//                    } else {
//                        stMin.getItems().addAll(String.valueOf(i));
//                        fiMin.getItems().addAll(String.valueOf(i));
//                    }
//                }
//            }
//        });
//    }
//
//    @FXML
//    public void handleSaveAction(ActionEvent event) throws IOException {
////        System.out.println(datePicker.getValue());
//        if (!(workList.checkNameWeekly(title.getText()))) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Unable to name");
//            alert.setContentText("Please enter the new name.");
//            alert.showAndWait();
//        } else if (title.getText().equals("")) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please enter the work title.");
//            alert.showAndWait();
//        } else if (datePicker.getValue() == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please select the date.");
//            alert.showAndWait();
//        }  else if (stHour.getValue() == null && stMin.getValue() == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please specify the start time.");
//            alert.showAndWait();
//        } else if (fiHour.getValue() == null && fiMin.getValue() == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please specify the end time.");
//            alert.showAndWait();
//        } else if (Integer.parseInt(stHour.getValue()) == Integer.parseInt(fiHour.getValue()) &&
//                (Integer.parseInt(stMin.getValue()) > Integer.parseInt(fiMin.getValue()))) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please check and complete the correct time.");
//            alert.show();
//        } else if (Integer.parseInt(stHour.getValue()) > Integer.parseInt(fiHour.getValue())) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please check and complete the correct time.");
//            alert.show();
//        } else if (Integer.parseInt(stHour.getValue()) == Integer.parseInt(fiHour.getValue()) &&
//                Integer.parseInt(stMin.getValue()) == Integer.parseInt(fiMin.getValue())) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please check and complete the correct time.");
//            alert.show();
//        } else if (priority.getValue() == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please select the priority.");
//            alert.showAndWait();
//        } else if (category.getValue() == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please select the category.");
//            alert.showAndWait();
//        } else {
//            if (!(title.getText().equals("")) && datePicker.getValue() != null && stHour.getValue() != null
//                    && stMin.getValue() != null && fiHour.getValue() != null &&
//                    fiMin.getValue() != null && priority.getValue() != null &&
//                    category.getValue() != null) {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("CONFIRMATION");
//                alert.setHeaderText("Please check the information is correct!?");
//                alert.showAndWait();
//                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//                alert1.setTitle("INFORMATION");
//                alert1.setHeaderText("It has been done (already)");
//                alert1.show();
//            }
//            weeklyTask = new DetailWeekly(title.getText(), datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                    stHour.getValue() + ":" + stMin.getValue(),
//                    fiHour.getValue() + ":" + fiMin.getValue(),
//                    priority.getValue(), "Not started", category.getValue(),
//                    "Not delivery yet", sendDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//
////            System.out.println(weeklyTask.toString());
//            title.clear();
//            datePicker.setValue(null);
//            stHour.setValue(null);
//            stMin.setValue(null);
//            fiHour.setValue(null);
//            fiMin.setValue(null);
//            priority.setValue(null);
//            category.setValue(null);
//            sendDatePicker.setValue(null);
//            workList.addWeekly(weeklyTask);
//            dataSource.setData(workList);
//        }
//    }
//
//
//    @FXML
//    public void handleBackWeeklyJobToHomeButton(ActionEvent actionEvent) {
//        try {
//            FXRouter.goTo("home");
//        } catch (IOException e) {
//            System.err.println("ไปที่หน้า home ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
//        }
//    }
//}
//
