package main.java.com.yourcompany.hdapp.controllers;

import com.yourcompany.hdapp.models.Task;
import com.yourcompany.hdapp.services.FirestoreService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TaskController {
    private FirestoreService firestoreService;

    public TaskController(FirestoreService firestoreService) {
        this.firestoreService = firestoreService;
    }

    public List<Task> getTasks() throws ExecutionException, InterruptedException {
        return firestoreService.getTasks();
    }

    public void addTask(Task task) throws ExecutionException, InterruptedException {
        firestoreService.addTask(task);
    }

    public void deleteTask(String taskId) throws ExecutionException, InterruptedException {
        firestoreService.deleteTask(taskId);
    }
}
