package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskController implements TaskService{
    @Override
    public boolean addTask(Task task) {
        String SQL = "INSERT INTO task VALUES(?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, task.getTitle());

            boolean b = psTm.executeUpdate() > 0;

            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Task Added!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public ObservableList<Task> getAll() {
        ObservableList<Task> taskObservableList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM task";
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getString("Title")
                );
                taskObservableList.add(task);
                System.out.println(task);
            }
            return taskObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
