package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import connectivity.ConnectionClass;
import manageLogic.Student;

import java.net.URL;
import java.sql.*;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button searchStudent;
    public Button deleteStudent;
    public Button addStudent;
    public TableView<Student> studentsTableView;
    public TableColumn<Student, Integer> col_id_student;
    public TableColumn<Student, String> col_name_student;
    public TableColumn<Student, String> col_surname_student;
    public TableColumn<Student, String> col_fathersName_student;
    public TableColumn<Student, String> col_birthdate_student;
    public TableColumn<Student, String> col_group_student;

    ObservableList<Student> oblist = FXCollections.observableArrayList();

    public void pressSearchStudentButton(ActionEvent actionEvent) {
        TextField inputName = new TextField();
        TextField inputSurname = new TextField();
        TextField inputFathersname = new TextField();
        TextField inputGroup = new TextField();
        GridPane pane = new GridPane();
        pane.add(inputName, 1, 0);
        pane.add(inputSurname, 2 ,0);
        pane.add(inputFathersname, 3, 0);
        pane.add(inputGroup, 4, 0);
        Button btn = new Button("Поиск");
        pane.add(btn, 5, 0);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {



                try(ConnectionClass connection = new ConnectionClass()){


                }catch (SQLException ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText(ex.getSQLState());
                }

            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_id_student.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name_student.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname_student.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_fathersName_student.setCellValueFactory(new PropertyValueFactory<>("fathersName"));
        col_birthdate_student.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        col_group_student.setCellValueFactory(new PropertyValueFactory<>("group"));
        try(ConnectionClass connection = new ConnectionClass()) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
