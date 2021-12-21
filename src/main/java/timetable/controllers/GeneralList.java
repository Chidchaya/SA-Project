package timetable.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import timetable.models.DetailGeneralBasic;
import timetable.models.WorkList;
import timetable.services.CategoryReadWrite;
import timetable.services.DataSource;
import timetable.services.StringConfig;
import timetable.services.GeneralReadWrite;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class GeneralList {
    private DataSource data, categorySource;
    private WorkList list, categoryList, searchList;
    private ObservableList<DetailGeneralBasic> generalObservableList, searchObservableList;
    private DetailGeneralBasic selectedTask;


    @FXML Button backToHome, update, remove, searchBtn;
    @FXML
    TextField search;
    @FXML
    ChoiceBox<String> priority, status, category, sendStatus, smlxl;
    @FXML
    ChoiceBox<String> stHour, stMin, fiHour, fiMin;
    @FXML
    DatePicker datePicker, sendDatePicker;
    @FXML
    Label size, title;

    @FXML private TableView<DetailGeneralBasic> infoGeneralTable;

    @FXML
    private ImageView background;

    @FXML
    public void initialize() {
        background.setImage(new Image("/images/Background 3.png"));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                data = new GeneralReadWrite("data","general.csv");
                list = data.getData();
                update.setDisable(true);
                remove.setDisable(true);
                categorySource = new CategoryReadWrite("data", "category.csv");
                categoryList = categorySource.getData();
                for (int i = 0; i < categoryList.toListCategory().size(); i++) {
                    category.getItems().add(categoryList.toListCategory().get(i).getNameCategory());
                }
                category.getItems().add("***Don't have***");
                status.getItems().addAll("Not started", "Process", "Finish");
                smlxl.getItems().addAll("xs", "s", "m", "l", "xl", "xxl");
                sendStatus.getItems().addAll("Not delivery yet", "Self pick up", "Delivery");
                priority.getItems().addAll("Design", "Repair");
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
                showData();
            }
        });
        infoGeneralTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedTask((DetailGeneralBasic) newValue);
            }
        });
    }

    public void showSelectedTask(DetailGeneralBasic detailGeneralBasic) {
        selectedTask = detailGeneralBasic;
        title.setText(detailGeneralBasic.getTitle());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") ;
        LocalDate doDateLocalDatetime = LocalDate.parse(detailGeneralBasic.getDate(), formatter);
        datePicker.setValue(doDateLocalDatetime);
        stHour.setValue(detailGeneralBasic.getStartTime().substring(0,2));
        stMin.setValue(detailGeneralBasic.getStartTime().substring(3,5));
        fiHour.setValue(detailGeneralBasic.getEndTime().substring(0,2));
        fiMin.setValue(detailGeneralBasic.getEndTime().substring(3,5));
        priority.setValue(detailGeneralBasic.getPriority());
        status.setValue(detailGeneralBasic.getStatus());
        category.setValue(detailGeneralBasic.getCategory());
        size.setText(detailGeneralBasic.getSize());
        smlxl.setValue(detailGeneralBasic.getSmlxl());
        sendStatus.setValue(detailGeneralBasic.getSendStatus());
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd") ;
        LocalDate doSendDateLocalDatetime = LocalDate.parse(detailGeneralBasic.getSendDate(), formatter1);
        sendDatePicker.setValue(doSendDateLocalDatetime);
        if (selectedTask.getStatus().equals("Finish")) {
            remove.setDisable(false);
        }
        update.setDisable(false);
    }

    private void showData() {
        generalObservableList = FXCollections.observableArrayList(list.toGeneralList());
        infoGeneralTable.setItems(generalObservableList);

        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("title:ID-Order", "field:title"));
        configs.add(new StringConfig("title:Date", "field:date"));
        configs.add(new StringConfig("title:Start-Time", "field:startTime"));
        configs.add(new StringConfig("title:Finish-Time", "field:endTime"));
        configs.add(new StringConfig("title:Type", "field:priority"));
        configs.add(new StringConfig("title:Status", "field:status"));
        configs.add(new StringConfig("title:Category", "field:category"));
        configs.add(new StringConfig("title:ID-Customer", "field:size"));
        configs.add(new StringConfig("title:Size", "field:smlxl"));
        configs.add(new StringConfig("title:Send-Status", "field:sendStatus"));
        configs.add(new StringConfig("title:Send-Date", "field:sendDate"));


        for (StringConfig conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            infoGeneralTable.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                infoGeneralTable.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
            infoGeneralTable.refresh();
        }
    }

    @FXML
    public void handleUpdateAction(ActionEvent event) throws IOException {
        if (title.getText().equals("") && datePicker.getValue() == null && stHour.getValue() == null &&
                stMin.getValue() == null && fiHour.getValue() == null &&
                fiMin.getValue() == null && priority.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please fill out all information.");
            alert.showAndWait();
        } else if (title.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please enter the work title.");
            alert.showAndWait();
        } else if (datePicker.getValue() == null) {
            //System.out.println("Error");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please select the date.");
            alert.showAndWait();
        } else if (stHour.getValue() == null && stMin.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please specify the start time.");
            alert.showAndWait();
        } else if (fiHour.getValue() == null && fiMin.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please specify the end time.");
            alert.showAndWait();
        } else if (sendDatePicker.getValue().isBefore(datePicker.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("The delivery date can't be less than the cut-off date. Please enter the new date.");
            alert.showAndWait();
//            System.out.println("Tis");
        } else if (Integer.parseInt(stHour.getValue()) == Integer.parseInt(fiHour.getValue()) &&
                (Integer.parseInt(stMin.getValue()) > Integer.parseInt(fiMin.getValue()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check and complete the correct time.");
            alert.show();
        } else if (Integer.parseInt(stHour.getValue()) > Integer.parseInt(fiHour.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check and complete the correct time.");
            alert.show();
        } else if (Integer.parseInt(stHour.getValue()) == Integer.parseInt(fiHour.getValue()) &&
                Integer.parseInt(stMin.getValue()) == Integer.parseInt(fiMin.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check and complete the correct time.");
            alert.show();
        } else if (priority.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please select the priority.");
            alert.showAndWait();
        } else if (size.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please enter the Breast, Waist, Hips");
            alert.showAndWait();
        } else if (smlxl.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please select the new size.");
            alert.showAndWait();
        } else if (selectedTask.getStatus().equals("Finish")) {
                title.setText(selectedTask.getTitle());
                priority.setValue(selectedTask.getPriority());
                status.setValue(selectedTask.getStatus());
                update.setDisable(true);
                selectedTask.update(selectedTask.getTitle(),
                        selectedTask.getDate(),
                        selectedTask.getStartTime(),
                        selectedTask.getEndTime(),
                        selectedTask.getPriority(),
                        selectedTask.getStatus(),
                        selectedTask.getCategory(),
                        selectedTask.getSize(),
                        selectedTask.getSmlxl(),
                        sendStatus.getValue(),
                        sendDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } else if (category.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please select the category.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText("Please check to update is correct!?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                selectedTask.update(title.getText(),
                        datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        stHour.getValue() + ":" + stMin.getValue(),
                        fiHour.getValue() + ":" + fiMin.getValue(),
                        priority.getValue(), status.getValue(), category.getValue(),
                        size.getText(), smlxl.getValue(), sendStatus.getValue(),
                        sendDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                try {
                    FXRouter.goTo("general List");
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า home ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                }
            } else {}
            System.out.println(selectedTask.toString());
            data.setData(list);

            }
        infoGeneralTable.refresh();
//        showData();
    }

    private void showSearchData() {
//        infoGeneralTable.getItems().clear();
        searchObservableList = FXCollections.observableArrayList(selectedTask);
        infoGeneralTable.setItems(searchObservableList);

        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("title:ID", "field:title"));
        configs.add(new StringConfig("title:Date", "field:date"));
        configs.add(new StringConfig("title:Start-Time", "field:startTime"));
        configs.add(new StringConfig("title:Finish-Time", "field:endTime"));
        configs.add(new StringConfig("title:Type", "field:priority"));
        configs.add(new StringConfig("title:Status", "field:status"));
        configs.add(new StringConfig("title:Category", "field:category"));
        configs.add(new StringConfig("title:Title", "field:size"));
        configs.add(new StringConfig("title:Size", "field:smlxl"));
        configs.add(new StringConfig("title:Send-Status", "field:sendStatus"));
        configs.add(new StringConfig("title:Send-Date", "field:sendDate"));


        for (StringConfig conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            infoGeneralTable.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                infoGeneralTable.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
            infoGeneralTable.refresh();
        }
    }

    @FXML
    public void handleRemoveButton(ActionEvent actionEvent) {
        if (selectedTask.getStatus().equals("Finish") && (sendDatePicker.getValue() != null)) {
            if (selectedTask.getSendStatus().equals("Self pick up") ||
                    selectedTask.getSendStatus().equals("Delivery")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMATION");
                alert.setHeaderText("Do you want to delivery this work?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    list.removeGeneral(selectedTask);
                    data.setData(list);
                    try {
                        FXRouter.goTo("general List");
                    } catch (IOException e) {
                        System.err.println("ไปที่หน้า General List ไม่ได้");
                        System.err.println("ให้ตรวจสอบการกำหนด route");
                    }
                }
            }
//                showData();
            infoGeneralTable.refresh();
        } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Can't Delivery");
                alert.setContentText("Send status isn't Self pick up or Delivery");
                alert.showAndWait();
        }
    }

    @FXML
    public void handleSearchButton(ActionEvent actionEvent) {
        selectedTask = list.searchName(search.getText());
        if(selectedTask == null){
            showData();
        } else {
            showSearchData();
        }
    }


    @FXML
    public void handleBackToHomeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
