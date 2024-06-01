package main.java.com.yourcompany.hdapp;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.yourcompany.hdapp.controllers.TaskController;
import com.yourcompany.hdapp.models.Task;
import com.yourcompany.hdapp.services.FirestoreService;
import com.yourcompany.hdapp.views.TaskView;

import javax.swing.*;

public class MainFrame extends JFrame {
    private TaskController taskController;

    public MainFrame() {
        Firestore firestore = FirestoreOptions.getDefaultInstance().getService();
        FirestoreService firestoreService = new FirestoreService(firestore);
        this.taskController = new TaskController(firestoreService);

        Task task = new Task(); // Assuming a Task object is needed

        TaskView taskView = new TaskView(taskController, task);
        add(taskView);

        setTitle("Main Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
