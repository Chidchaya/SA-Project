package timetable.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import timetable.models.*;
import timetable.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class CategoryController {
    @FXML TextField titleCategory;
    @FXML TextArea showCategoryTextArea;
    @FXML private TableView<DetailCategory> infoCategoryTable;

//    @FXML Button update;

    private DetailCategory selectedTask;
    private WorkList workList, genWorkList, weekWorkList, forWorkList, proWorkList;
    private DataSource dataSource, genDataSource, weekDataSource, forDataSource, proDataSource;
    private ObservableList<DetailCategory> categoryObservableList;

    @FXML
    private ImageView background;

    @FXML
    public void initialize() {
        background.setImage(new Image("/images/Background 4.png"));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                workListCategory = new CategoryReadWrite()
                dataSource = new CategoryReadWrite("data","category.csv");
                workList = dataSource.getData();
                genDataSource = new GeneralReadWrite("data", "general.csv");
                genWorkList = genDataSource.getData();
//                weekDataSource = new WeeklyReadWrite("data", "weekly.csv");
//                weekWorkList = weekDataSource.getData();
//                forDataSource = new ForwardReadWrite("data", "forward.csv");
//                forWorkList = forDataSource.getData();
////                proDataSource = new ProjectReadWrite("data", "project.csv");
//                proWorkList = proDataSource.getData();
//                update.setDisable(true);
                showDataCategory();
            }
        });
        infoCategoryTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showCategoryTask((DetailCategory) newValue);
            }
        });
    }

    public void handleToCreateCategoryButton(javafx.event.ActionEvent actionEvent) {
        if (titleCategory.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please enter the name of category.");
            alert.showAndWait();
        } else if (!(workList.checkNameCategory(titleCategory.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to Category name");
            alert.setContentText("Please enter the new Category name.");
            alert.showAndWait();
        } else {
            if (!(titleCategory.getText().equals(""))) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("CONFIRMATION");
                alert.setHeaderText("Please check the name Category is correct!?");
                alert.showAndWait();
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("INFORMATION");
                alert1.setHeaderText("It has been done (already)");
                alert1.show();
            }
            selectedTask = new DetailCategory(titleCategory.getText()) ;
            workList.addCategory(selectedTask);
            dataSource.setData(workList);
        }
        infoCategoryTable.refresh();
        titleCategory.clear();
        showDataCategory();
    }

    public void showCategoryTask(DetailCategory detailCategory) {
        selectedTask = detailCategory;
        titleCategory.setText(detailCategory.getNameCategory());
//        update.setDisable(false);
        showCategoryTextArea(detailCategory.getNameCategory());
    }

//    @FXML
//    public void handleUpdateAction(ActionEvent event) throws IOException {
//        if ( titleCategory.getText().equals("")) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Incomplete information");
//            alert.setContentText("Please enter the name of category.");
//            alert.showAndWait();
//        } else if (!(workList.checkNameCategory(titleCategory.getText()))) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Unable to Category name");
//            alert.setContentText("Please enter the new Category name.");
//            alert.showAndWait();
//        } else {
//            selectedTask.update(titleCategory.getText());
//            System.out.println(selectedTask.toString());
//            dataSource.setData(workList);
//            infoCategoryTable.refresh();
//        }
//        titleCategory.clear();
//    }

    private void showCategoryTextArea(String name) {
        showCategoryTextArea.setText(null);
        showCategoryTextArea.appendText("Design Works;\n");
        showCategoryTextArea.appendText("Design work category has " + genWorkList.generalCount(name).size() + " works\n");
        for (DetailGeneralBasic g : genWorkList.generalCount(name)) {
            showCategoryTextArea.appendText("- " + g.toString() + "\n");
        }
//        showCategoryTextArea.appendText("\n");
//        showCategoryTextArea.appendText("Repair Works;\n");
//        showCategoryTextArea.appendText("Repair work category has " + weekWorkList.weeklyCount(name).size() + " works\n");
//        for (DetailWeekly w : weekWorkList.weeklyCount(name)) {
//            showCategoryTextArea.appendText("- " + w.toString() + "\n");
//        }
//        showCategoryTextArea.appendText("\n");
//        showCategoryTextArea.appendText("Forward Works;\n");
//        showCategoryTextArea.appendText("Forward work category has " + forWorkList.forwardCount(name).size() + " works\n");
//        for (DetailForward f : forWorkList.forwardCount(name)) {
//            showCategoryTextArea.appendText("- " + f.toString() + "\n");
//        }
//        showCategoryTextArea.appendText("\n");
//        showCategoryTextArea.appendText("Project Works;\n");
//        showCategoryTextArea.appendText("Project work category has " + proWorkList.projectCount(name).size() + " works\n");
//        for (DetailProject p : proWorkList.projectCount(name)) {
//            showCategoryTextArea.appendText("- " + p.toString() + "\n");
//        }
    }

    private void showDataCategory() {
        infoCategoryTable.getColumns().clear();
        categoryObservableList = FXCollections.observableArrayList(workList.toListCategory());
        infoCategoryTable.setItems(categoryObservableList);

        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("title:Name Category", "field:nameCategory"));

        for (StringConfig conf : configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.prefWidthProperty().bind(infoCategoryTable.widthProperty().multiply(1.0));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            infoCategoryTable.getColumns().add(col);
            infoCategoryTable.getSortOrder().addAll(col);
            col.setSortType(TableColumn.SortType.ASCENDING);
            infoCategoryTable.refresh();
        }
    }

    @FXML
    public void handleBackCategoryToHomeButton(javafx.event.ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
