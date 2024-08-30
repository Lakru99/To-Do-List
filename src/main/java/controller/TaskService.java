package controller;

import javafx.collections.ObservableList;
import model.Task;

public interface TaskService {
    boolean addTask(Task task);
    public ObservableList<Task> getAll();
}
