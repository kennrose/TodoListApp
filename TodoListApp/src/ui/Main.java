package ui;

import controller.TaskController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import model.TaskManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.WARNING, "Could not set system look and feel.", ex);
        }

        TaskManager taskManager = new TaskManager();
        taskManager.loadTasks();
        TaskController controller = new TaskController(taskManager);

        java.awt.EventQueue.invokeLater(() -> {
            MainForm view = new MainForm(controller);
            controller.setView(view);
            view.setVisible(true);
        });
    }
}
