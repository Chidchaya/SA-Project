package timetable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import timetable.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseController {
    @FXML
    private Label welcomeText,welcomeText2;
    @FXML
    public void onHelloButtonClick(ActionEvent event) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.ConnectDb()
                ;

        String connectQuery = "SELECT id FROM world.test1;";
        String connectQuery2 = "SELECT name FROM world.test1;";


        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);
            Statement statement2 = connectDB.createStatement();
            ResultSet queryOutput2 = statement2.executeQuery(connectQuery2);

            while(queryOutput.next()){
                welcomeText.setText(queryOutput.getString("id"));

            }
            while(queryOutput2.next()){
                welcomeText2.setText(queryOutput2.getString("name"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        //welcomeText.setText("Welcome to JavaFX Application!");
    }


}
