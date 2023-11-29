package com.example.docrepositorytry;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class HelloController extends MySQL_DB implements Initializable {
    MySQL_DB mysqlDB = new MySQL_DB();
    String url = "jdbc:mysql://213.167.207.249:3306/doc-repository";
    public Connection connection;
    String user = "c1berk0tleta";
    String password = "os1R$USNES$";

    ObservableList<Realty> listRealty;

    @FXML
    private TableColumn<Realty, Integer> realtyAddressID;

    @FXML
    private TableColumn<Realty, Integer> realtyID;

    @FXML
    private TableColumn<Realty, String> realtyName;

    @FXML
    private TableColumn<Realty, Integer> realtyPhoto;

    @FXML
    private TableColumn<Realty, Integer> realtyStatusID;

    @FXML
    private TableColumn<Realty, Integer> realtyType;

    @FXML
    private MenuItem menuTables_realty;
    @FXML
    private Menu tables;
    @FXML
    private TextField logField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button authButton;
    @FXML
    private Text textAuthError;
    @FXML
    private AnchorPane logPane;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableView<Realty> tableRealty;

    @FXML
    protected void Login() throws InterruptedException {
//        String query = "SELECT login, password FROM users";
        String query = "SELECT login, password FROM users WHERE login LIKE '"+logField.getText()+"'";
        String loginAuth = "";
        String passAuth = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                loginAuth = resultSet.getString(1);
                passAuth = resultSet.getString(2);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        if (logField.getText().equals("") | passField.getText().equals("")) {
            textAuthError.setVisible(true);
            textAuthError.setText("Введите логин и пароль");
        } else if (logField.getText().equals(loginAuth) && passField.getText().equals(passAuth)) {
            logPane.setVisible(false);
            mainPane.setVisible(true);

        } else {
            textAuthError.setText("Неверный логин или пароль!");
            textAuthError.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConnectDB();
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Подключение к базе данных успешно установлено!");
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных:");
        }

    }

    protected void outputRealty() {
        try {
            realtyID.setCellValueFactory(new PropertyValueFactory<Realty, Integer>("ID"));
            realtyName.setCellValueFactory(new PropertyValueFactory<Realty, String>("name"));
            realtyAddressID.setCellValueFactory(new PropertyValueFactory<Realty, Integer>("address"));
            realtyType.setCellValueFactory(new PropertyValueFactory<Realty, Integer>("type"));
            realtyStatusID.setCellValueFactory(new PropertyValueFactory<Realty, Integer>("status"));
            realtyPhoto.setCellValueFactory(new PropertyValueFactory<Realty, Integer>("photo"));
            listRealty = mysqlDB.listRealty;
            tableRealty.setItems(listRealty);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLException: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}