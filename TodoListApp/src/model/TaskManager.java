package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.IdGenerator;

public class TaskManager {
    private static final Logger LOGGER = Logger.getLogger(TaskManager.class.getName());
    private static final String FILE_PATH = "taskslist.ser";
    private HashMap<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        if (task == null) throw new IllegalArgumentException("Task cannot be null.");
        String id;
        do {
            id = IdGenerator.generateId();
        } while (tasks.containsKey(id));
        task.setPnum(id);
        tasks.put(id, task);
    }

    public Task deleteTask(String pnum) { return tasks.remove(pnum); }
    public Task findTask(String pnum) { return tasks.get(pnum); }
    public HashMap<String, Task> findAll() { return new HashMap<>(tasks); }

    public void setTask(Task task) {
        if (task != null && task.getPnum() != null && tasks.containsKey(task.getPnum())) {
            tasks.put(task.getPnum(), task);
        }
    }

    public void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            tasks = new HashMap<>();
            return;
        }
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            Object loaded = input.readObject();
            if (loaded instanceof HashMap<?, ?> map) {
                HashMap<String, Task> validTasks = new HashMap<>();
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    if (entry.getKey() instanceof String key && entry.getValue() instanceof Task task) {
                        validTasks.put(key, task);
                    }
                }
                tasks = validTasks;
            } else {
                tasks = new HashMap<>();
            }
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.WARNING, "Could not load saved tasks. Starting with an empty list.", ex);
            tasks = new HashMap<>();
        }
    }

    public int saveTasks() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            output.writeObject(tasks);
            return 0;
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Could not save tasks.", ex);
            return 1;
        }
    }
}
