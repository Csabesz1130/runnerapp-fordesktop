package main.java.com.yourcompany.hdapp.views;

import com.yourcompany.hdapp.MainFrame;
import com.yourcompany.hdapp.models.Task;
import com.yourcompany.hdapp.services.FirestoreService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TaskView extends JPanel {
    private MainFrame mainFrame;
    private FirestoreService firestoreService;
    private DefaultListModel<Task> taskListModel;

    public TaskView(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.firestoreService = new FirestoreService();
        this.taskListModel = new DefaultListModel<>();

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Tasks", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JList<Task> taskList = new JList<>(taskListModel);
        taskList.setCellRenderer(new TaskRenderer());
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener((ActionEvent e) -> addTask());
        JButton deleteButton = new JButton("Delete Task");
        deleteButton.addActionListener((ActionEvent e) -> deleteTask(taskList.getSelectedValue()));

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        loadTasks();
    }

    private void loadTasks() {
        taskListModel.clear();
        List<Task> tasks = firestoreService.getAllTasks();
        if (tasks != null) {
            tasks.forEach(taskListModel::addElement);
        } else {
            // Add some dummy data for demonstration
            taskListModel.addElement(new Task("1", "Task 1", "Description 1", "Pending"));
            taskListModel.addElement(new Task("2", "Task 2", "Description 2", "Completed"));
        }
    }

    private void addTask() {
        String id = JOptionPane.showInputDialog(this, "Enter Task ID:");
        String name = JOptionPane.showInputDialog(this, "Enter Task Name:");
        String description = JOptionPane.showInputDialog(this, "Enter Task Description:");
        String status = JOptionPane.showInputDialog(this, "Enter Task Status:");

        Task task = new Task(id, name, description, status);
        firestoreService.addTask(task);
        loadTasks();
    }

    private void deleteTask(Task task) {
        if (task != null) {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                firestoreService.deleteTask(task.getId());
                loadTasks();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.", "No Task Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private class TaskRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (c instanceof JLabel && value instanceof Task) {
                Task task = (Task) value;
                ((JLabel) c).setText(task.getName() + " - " + task.getDescription() + " [" + task.getStatus() + "]");
            }
            return c;
        }
    }
}
