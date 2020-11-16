package Controllers;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;
import connectivity.ConnectionClass;
import manageLogic.Student;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    public Button deleteStudent;
    public Button addStudent;
    public TableView<Student> studentsTableView;
    public TableColumn<Student, String> col_id_student;
    public TableColumn<Student, String> col_name_student;
    public TableColumn<Student, String> col_surname_student;
    public TableColumn<Student, String> col_fathersName_student;
    public TableColumn<Student, String> col_birthday_student;
    public TableColumn<Student, String> col_group_student;

    public void pressAddStudentButton(ActionEvent actionEvent) {

        TextField inputName = new TextField();
        TextField inputSurname = new TextField();
        TextField inputFathersname = new TextField();
        TextField inputBirthday = new TextField();
        TextField inputGroup = new TextField();
        GridPane pane = new GridPane();


        Label name = new Label("Имя");
        Label surname = new Label("Фамилия");
        Label fathername = new Label("Отчество");
        Label birthday = new Label("Дата рождения");
        Label group = new Label("Группа");
        Label success = new Label();

        pane.add(name, 1, 0);
        pane.add(surname, 2, 0);
        pane.add(fathername, 3, 0);
        pane.add(birthday, 4, 0);
        pane.add(group, 5, 0);
        pane.add(success, 6, 0);

        pane.add(inputName, 1, 1);
        pane.add(inputSurname, 2 ,1);
        pane.add(inputFathersname, 3, 1);
        pane.add(inputBirthday, 4,1 );
        pane.add(inputGroup, 5, 1);
        Button btn = new Button("Добавить");
        pane.add(btn, 6, 1);
        Scene scene = new Scene(pane);
        Stage startStage = new Stage();
        startStage.setScene(scene);
        startStage.show();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try(ConnectionClass connection = new ConnectionClass()){
                    Node node = (Node) actionEvent.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    String sql = "{call add_student(?, ?, ?, ?, ?)}";
                    var statement = connection.preparedStatement(sql);
                    statement.setString(1, inputName.getText());
                    statement.setString(2, inputSurname.getText());
                    statement.setString(3, inputFathersname.getText());
                    statement.setString(4, inputBirthday.getText());
                    statement.setString(5, inputGroup.getText());


                    statement.execute();
                    success.setText("Успешно добавлен");

                    stage.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Успешно добавлен");
                    studentsTableView.getItems().clear();
                    updateTable();

                }catch (SQLException ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText(ex.getSQLState());
                }

            }
        });

    }

    public void updateTable(){
        col_id_student.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name_student.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname_student.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_fathersName_student.setCellValueFactory(new PropertyValueFactory<>("fathersName"));
        col_birthday_student.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        col_group_student.setCellValueFactory(new PropertyValueFactory<>("group"));


        try(ConnectionClass connection = new ConnectionClass()) {
            String sql = "Select * from students";
            var res = connection.execQuery(sql);
            while (res.next()){
                Student student = new Student(res.getString(1), res.getString(2), res.getString(3),
                        res.getString(4), res.getString(5), res.getString(6));
                studentsTableView.getItems().add(student);
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(ex.getSQLState());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
    }


    public void deleteStudentButton(ActionEvent actionEvent) {

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("Введите номер студента:");
        var id = inputDialog.showAndWait().get();

        try(ConnectionClass connection = new ConnectionClass()) {
            String sql = "{call delete_student(?)}";
            var statement = connection.preparedStatement(sql);
            statement.setString(1, id);
            statement.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Успешно удален");
            alert.show();
            studentsTableView.getItems().clear();
            updateTable();
        }catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(ex.getSQLState());
            alert.show();
        }
    }
}

