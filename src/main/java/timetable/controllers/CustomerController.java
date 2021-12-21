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
import timetable.DatabaseConnection;
import timetable.models.CustomerConstructor;
import timetable.models.WorkList;
import timetable.services.CustomerReadWrite;
import timetable.services.DataSource;
import timetable.services.StringConfig;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CustomerController {
    @FXML
    TextField name, tel;
    @FXML
    Button createBtn, backBtn;

    @FXML private TableView<CustomerConstructor> infoCustomerTable;

    @FXML
    private ImageView background;

    private CustomerConstructor customerTask;
    private WorkList list;
    private DataSource data;
    private ObservableList<CustomerConstructor> customerObservableList;
    Connection conn =null;
    //ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    public void initialize() {
        background.setImage(new Image("/images/Background 4.png"));
        Platform.runLater((Runnable) new Runnable() {
            @Override
            public void run() {
                data = new CustomerReadWrite("data", "customer.csv");
                list = data.getData();
                showData1();
            }
        });
        infoCustomerTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showCustomerTask((CustomerConstructor) newValue);
            }
        });
    }

    public void showCustomerTask(CustomerConstructor customerConstructor) {
        customerTask = customerConstructor;
        name.setText(customerConstructor.getName());
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

    @FXML
    public void handleCreateCustomerAction(ActionEvent event) throws IOException {
        if (!(list.checkCustomerTel(tel.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("This telephone number exists");
            alert.showAndWait();
            name.clear();
            tel.clear();
        } else if (name.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check the information");
            alert.showAndWait();
        } else if (name.getText().equals("") && tel.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete information");
            alert.setContentText("Please check the information");
            alert.showAndWait();
        } else {
            customerTask = new CustomerConstructor(name.getText(),
                    String.valueOf(list.runCustomerId()), tel.getText());
            System.out.println(customerTask.toString());
            data.setData(list);
            try {
                FXRouter.goTo("customer");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า home ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
            conn = DatabaseConnection.ConnectDb();
            String sql = "insert into customer (name,idCustomer,telNum)values(?,?,? )";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, name.getText());
                pst.setString(2, String.valueOf(list.runCustomerId()));
                pst.setString(3, tel.getText());
                pst.execute();

                JOptionPane.showMessageDialog(null, "Users Add succes");
                sql = "commit";

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            name.clear();
            tel.clear();
            list.addCustomer(customerTask);
            data.setData(list);
            infoCustomerTable.refresh();
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
