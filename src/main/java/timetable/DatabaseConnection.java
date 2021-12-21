package timetable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetable.models.test1;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {
    public Connection databaseLink;

//    public Connection getConnection() {
//        String databaseName = "world";
//        String databaseUser = "imyingto";
//        String databasePassword = "im1ying2to3";
//        String url = "jdbc:mysql://localhost/" + databaseName;
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return databaseLink;
//
//    }

    Connection conn = null;

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/world", "imyingto", "im1ying2to3");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ObservableList<Object> getDatausers() {
        Connection conn = ConnectDb();
        ObservableList<Object> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from world.test1");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new test1(Integer.parseInt(rs.getString("id")), rs.getString("name")));
            }
        }
        catch(Exception e){

            }
            return list;
        }


    }



