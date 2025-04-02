package todo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import todo.model.Task;
import todo.storage.StorageService;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML private TextField taskInput;
    @FXML private Button addButton;
    @FXML private VBox taskList;


    private List<Task> tasks = new ArrayList<>();

    @FXML
    public void initialize() {
        // Load saved tasks
        tasks = StorageService.loadTasks();
        renderTasks();

        // Initialize Button
        addButton.setOnAction(event -> {
            String input = taskInput.getText().trim();
            if (!input.isEmpty()) {
                Task newTask = new Task(input);
                tasks.add(newTask);
                taskInput.clear();
                StorageService.saveTasks(tasks);
                renderTasks();
            }
        });
    }

    private void renderTasks() {
        taskList.getChildren().clear();

        for (Task task : tasks) {
            // Create a row with a label and a delete button
            HBox row = new HBox(10);
            Label label = new Label(task.getDescription());
            Button deleteButton = new Button("X");

            deleteButton.setOnAction(event -> {
                tasks.remove(task);
                StorageService.saveTasks(tasks);
                renderTasks();
            });

            row.getChildren().addAll(label, deleteButton);
            taskList.getChildren().add(row);
        }

    }
}
