package com.example.docrepositorytry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.sql.*;

public class MySQL_DB {
    public ObservableList<Realty> listRealty = FXCollections.observableArrayList();
    //public ObservableList<Orders> listO = FXCollections.observableArrayList();

    static Connection sqlConnect;

    public static Connection ConnectDB() {
        HelloController controller = new HelloController();
        String url = controller.url;
        String user = controller.user;
        String password = controller.password;
        try {
            sqlConnect = DriverManager.getConnection(url, user, password);
//            System.out.println("Подключение к базе данных успешно установлено!");
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных:");
            controller.printSQLException(e);
        }
        return null;
    }


    public void dataRealty() {
        ConnectDB();
        String query = "SELECT * FROM realty";
        try {
            Statement statement = sqlConnect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                listRealty.add(new Realty(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
}
