package ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

public class TaskTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Pnum", "Name", "Price", "Brand", "Model", "Specs"};
    private List<Task> tasks = new ArrayList<>();

    @Override public int getRowCount() { return tasks.size(); }
    @Override public int getColumnCount() { return columnNames.length; }
    @Override public String getColumnName(int column) { return columnNames[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = tasks.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> task.getPnum();
            case 1 -> task.getName();
            case 2 -> task.getPrice();
            case 3 -> task.getBrand();
            case 4 -> task.getModel();
            case 5 -> task.getSpecs();
            default -> null;
        };
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks == null ? new ArrayList<>() : new ArrayList<>(tasks);
        fireTableDataChanged();
    }

    public String getPnum(int rowIndex) { return tasks.get(rowIndex).getPnum(); }
    public Task getTask(int rowIndex) { return tasks.get(rowIndex); }
}
