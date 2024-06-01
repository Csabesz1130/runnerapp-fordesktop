package main.java.com.yourcompany.hdapp.views;

import com.yourcompany.hdapp.controllers.TaskController;
import com.yourcompany.hdapp.models.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskView extends JPanel {
    private TaskController taskController;
    private Task task;

    public TaskView(TaskController taskController, Task task) {
        this.taskController = taskController;
        this.task = task;

        setLayout(new BorderLayout());

        JLabel taskLabel = new JLabel("Task: " + task.getName());
        add(taskLabel, BorderLayout.NORTH);

        JButton deleteButton = new JButton("Delete Task");
        add(deleteButton, BorderLayout.SOUTH);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    taskController.deleteTask(task.getId());
                    JOptionPane.showMessageDialog(null, "Task deleted successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error deleting task: " + ex.getMessage());
                }
            }
        });
    }
}
