//package timetable.controllers;
//
//import com.github.saacsos.FXRouter;
//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import timetable.models.*;
//import timetable.services.*;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//
//public class ProjectList {
//    private DataSource data, categorySource;
//    private WorkList list, categoryList;
////    private ObservableList<DetailProject> projectObservableList;
////    private DetailProject selectedTask;
//
//    @FXML Button backToHome, update;
//    @FXML
//    TextField title, projectLeader;
//    @FXML
//    ChoiceBox<String> priority, status, category;
//    @FXML
//    ChoiceBox<String> stHour, stMin, fiHour, fiMin;
//    @FXML
//    DatePicker datePicker;
//
////    @FXML private TableView<DetailProject> infoProjectTable;
//
//    @FXML
//    private ImageView background;
//
//    @FXML
//    public void initialize() {
//        background.setImage(new Image("/images/Background 3.png"));
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                data = new ProjectReadWrite("data","project.csv");
//                list = data.getData();
//                update.setDisable(true);
//                categorySource = new CategoryReadWrite("data", "category.csv");
//                categoryList = categorySource.getData();
//                for (int i = 0; i < categoryList.toListCategory().size(); i++) {
//                    category.getItems().add(categoryList.toListCategory().get(i).getNameCategory());
//                }
//                category.getItems().add("***Don't have***");
//                status.getItems().addAll("Not started", "Process", "Finish");
//                priority.getItems().addAll("(3) Important", "(2) Medium", "(1) Lowest");
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
//                showData();
//            }
//        });
//        infoProjectTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                showSelectedTask((DetailProject) newValue);
//            }
//        });
//    }
//
//    public void showSelectedTask(DetailProject detailProject) {
//        selectedTask = detailProject;
//        title.setText(detailProject.getTitle());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") ;
//        LocalDate doDateLocalDatetime = LocalDate.parse(detailProject.getDate(),formatter) ;
//        datePicker.setValue(doDateLocalDatetime);
//        stHour.setValue(detailProject.getStartTime().substring(0,2));
//        stMin.setValue(detailProject.getStartTime().substring(3,5));
//        fiHour.setValue(detailProject.getEndTime().substring(0,2));
//        fiMin.setValue(detailProject.getEndTime().substring(3,5));
//        priority.setValue(detailProject.getPriority());
//        status.setValue(detailProject.getStatus());
//        projectLeader.setText(detailProject.getProjectLeader());
//        category.setValue(detailProject.getCategory());
//        update.setDisable(false);
//    }
//
//    @FXML
//    public void handleUpdateAction(ActionEvent event) throws IOException {
//        if (title.getText().equals("") && datePicker.getValue() == null && stHour.getValue() == null &&
//                stMin.getValue() == null && fiHour.getValue() == null &&
//                fiMin.getValue() == null && priority.getValue() == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please fill out all information.");
//            alert.showAndWait();
//        } else if (title.getText().equals("")) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please enter the work title.");
//            alert.showAndWait();
//        } else if (datePicker.getValue() == null) {
//            //System.out.println("Error");
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
//        } else if (selectedTask.getStatus().equals("Finish")) {
//            if (selectedTask.getStatus().equals("Finish")) {
//                title.setText(selectedTask.getTitle());
//                priority.setValue(selectedTask.getPriority());
//                status.setValue(selectedTask.getStatus());
//                update.setDisable(true);
//            } else {
//                title.setText(selectedTask.getTitle());
//                priority.setValue(selectedTask.getPriority());
//                status.setValue(selectedTask.getStatus());
//                update.setDisable(false);
//            }
//        } else if (category.getValue() == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please select the category.");
//            alert.showAndWait();
//        } else if (projectLeader.getText().equals("")) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please enter the project leader again.");
//            alert.showAndWait();
//        } else {
//            if (!(title.getText().equals("")) && datePicker.getValue() != null && stHour.getValue() != null
//                    && stMin.getValue() != null && fiHour.getValue() != null &&
//                    fiMin.getValue() != null && priority.getValue() != null &&
//                    category.getValue() != null && !(projectLeader.getText().equals(""))) {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("CONFIRMATION");
//                alert.setHeaderText("Please check to update is correct!?");
//                alert.showAndWait();
//                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//                alert1.setTitle("INFORMATION");
//                alert1.setHeaderText("It has been update (already)");
//                alert1.show();
//            }
//            selectedTask.update(title.getText(), datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                    stHour.getValue() + ":" + stMin.getValue(),
//                    fiHour.getValue() + ":" + fiMin.getValue(),
//                    priority.getValue(), status.getValue(), category.getValue(),
//                    projectLeader.getText());
//            System.out.println(selectedTask.toString());
//            data.setData(list);
//            infoProjectTable.refresh();
//        }
//    }
//
//    private void showData() {
//        projectObservableList = FXCollections.observableArrayList(list.toListProject());
//        infoProjectTable.setItems(projectObservableList);
//
//        ArrayList<StringConfig> configs = new ArrayList<>();
//        configs.add(new StringConfig("title:Name", "field:title"));
//        configs.add(new StringConfig("title:Date", "field:date"));
//        configs.add(new StringConfig("title:Start-Time", "field:startTime"));
//        configs.add(new StringConfig("title:Finish-Time", "field:endTime"));
//        configs.add(new StringConfig("title:Priority", "field:priority"));
//        configs.add(new StringConfig("title:Status", "field:status"));
//        configs.add(new StringConfig("title:Category", "field:category"));
//        configs.add(new StringConfig("title:Project Leader", "field:projectLeader"));
//
//        for (StringConfig conf: configs) {
//            TableColumn col = new TableColumn(conf.get("title"));
//            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
//            infoProjectTable.getColumns().add(col);
//            if (conf.get("title").equals("Priority")) {
//                infoProjectTable.getSortOrder().addAll(col);
//                col.setSortType(TableColumn.SortType.DESCENDING);
//            }
//            infoProjectTable.refresh();
//        }
//    }
//
//    @FXML
//    public void handleBackToHomeButton(ActionEvent actionEvent) {
//        try {
//            FXRouter.goTo("home");
//        } catch (IOException e) {
//            System.err.println("ไปที่หน้า home ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
//        }
//    }
//}
