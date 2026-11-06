package controller;

import java.util.ArrayList;
import java.util.List;
import model.Task;
import model.TaskManager;
import ui.MainForm;

public class TaskController {
    private final TaskManager model;
    private MainForm view;

    public TaskController(TaskManager model) { this.model = model; }
    public void setView(MainForm view) { this.view = view; }

    public boolean handleAddTask(String name, String price, String brand, String modelName, String specs) {
        if (isBlank(name) || isBlank(price) || isBlank(brand) || isBlank(modelName) || isBlank(specs)) {
            return false;
        }
        Task task = new Task();
        task.setName(name.trim());
        task.setPrice(price.trim());
        task.setBrand(brand.trim());
        task.setModel(modelName.trim());
        task.setSpecs(specs.trim());
        model.addTask(task);
        model.saveTasks();
        refreshView();
        return true;
    }

    public boolean handleUpdateTask(String pnum, String name, String price, String brand,
                                    String modelName, String specs) {
        if (isBlank(pnum) || isBlank(name) || isBlank(price) || isBlank(brand)
                || isBlank(modelName) || isBlank(specs)) return false;
        Task task = model.findTask(pnum);
        if (task == null) return false;
        task.setName(name.trim());
        task.setPrice(price.trim());
        task.setBrand(brand.trim());
        task.setModel(modelName.trim());
        task.setSpecs(specs.trim());
        model.setTask(task);
        model.saveTasks();
        refreshView();
        return true;
    }

    public boolean handleDeleteTask(String pnum) {
        Task deleted = model.deleteTask(pnum);
        if (deleted == null) return false;
        model.saveTasks();
        refreshView();
        return true;
    }

    public List<Task> getAllTask() { return new ArrayList<>(model.findAll().values()); }
    public Task getTaskDetails(String pnum) { return model.findTask(pnum); }

    private boolean isBlank(String value) { return value == null || value.trim().isEmpty(); }
    private void refreshView() { if (view != null) view.refreshTaskList(); }
}
