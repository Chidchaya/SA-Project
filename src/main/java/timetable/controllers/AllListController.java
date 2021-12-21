package timetable.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import timetable.models.*;
import timetable.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class AllListController {
    private DataSource dataGeneral;
    private WorkList listGeneral;
    private ObservableList<DetailGeneralBasic> generalObservableList;

    private DataSource data;
    private WorkList list;
    private ObservableList<CustomerConstructor> customerObservableList;

    private DetailCategory categoryTask;
    private DataSource dataSource;
    private WorkList listCategory;
    private ObservableList<DetailCategory> categoryObservableList;

    @FXML private TableView<DetailGeneralBasic> infoGeneralTable;
    @FXML private TableView<CustomerConstructor> infoCustomerTable;
    @FXML private TableView<DetailCategory> infoCategoryTable;

    @FXML Button updateGeneral, addCustomer;

    @FXML
    private ImageView background;

    @FXML
    public void initialize() {
        background.setImage(new Image("/images/Background 5.png"));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dataGeneral = new GeneralReadWrite("data", "general.csv");
                listGeneral = dataGeneral.getData();
                showDataGeneral();
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                data = new CustomerReadWrite("data","customer.csv");
                list = data.getData();
                showData1();
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dataSource = new CategoryReadWrite("data","category.csv");
                listCategory = dataSource.getData();
                showDataCategory();
            }
        });
    }

    private void showDataGeneral() {
        generalObservableList = FXCollections.observableArrayList(listGeneral.toGeneralList());
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

        for (StringConfig conf : configs) {
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

    private void showData1() {
        customerObservableList = FXCollections.observableArrayList(list.toCustomerList());
        infoCustomerTable.setItems(customerObservableList);

        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("title:ID", "field:idCustomer"));
        configs.add(new StringConfig("title:Name", "field:name"));
        configs.add(new StringConfig("title:Telephone", "field:telNumber"));

        for (StringConfig conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.prefWidthProperty().bind(infoCustomerTable.widthProperty().multiply(0.33));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            infoCustomerTable.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                infoCustomerTable.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
            infoCustomerTable.refresh();
        }
    }

    private void showDataCategory() {
        categoryObservableList = FXCollections.observableArrayList(listCategory.toListCategory());
        infoCategoryTable.setItems(categoryObservableList);

        ArrayList<StringConfig> configs = new ArrayList<>();
        configs.add(new StringConfig("title:Name Category", "field:nameCategory"));
//        configs.add(new StringConfig("title:All Work", "field:numOfWorks"));


        for (StringConfig conf : configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            col.prefWidthProperty().bind(infoCategoryTable.widthProperty().multiply(1.0));
            infoCategoryTable.getColumns().add(col);
            col.setSortType(TableColumn.SortType.ASCENDING);
        }
    }

    @FXML
    public void handleBackAllListToToHomeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleUpdateGeneralButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("general List");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า General List ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToCustomerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("customer");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Customer ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleToCategoryButton(javafx.event.ActionEvent actionEvent) {
        try {
            FXRouter.goTo("category");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Category ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
