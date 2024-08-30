package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Task;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController  implements Initializable {
    @FXML
    private JFXTextField addTaskField;
    @FXML
    private Label completedtask;

    @FXML
    private VBox vBox;
    @FXML
    private VBox vBox1;
    private JFXCheckBox box1;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<?, ?> colTitle;

    TaskService service = new TaskController();

    @FXML
    void addTaskOnAction(ActionEvent event) {
        box1 = new JFXCheckBox(addTaskField.getText());
        vBox.getChildren().addAll(box1);
        vBox.setSpacing(10);
        addTaskField.setText(" ");

        box1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                completedtask = new Label(box1.getText());
                vBox1.getChildren().add(completedtask);
                vBox.getChildren().removeAll(box1);
            }
            Task task = new Task(
                    completedtask.getText()
            );

            if(service.addTask(task)){
                new Alert(Alert.AlertType.INFORMATION,"Task Added !!").show();
            };

        });
    }

    @FXML
    void reloadOnAction(ActionEvent event) {
        loadTable();
    }
    private void loadTable() {
        ObservableList<Task> taskObservableList =service.getAll();
        taskTable.setItems(taskObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    }

}
