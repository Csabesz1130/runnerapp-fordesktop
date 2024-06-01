package com.yourcompany.hdapp.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.yourcompany.hdapp.models.Task;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class FirestoreService {
    private Firestore db;

    public FirestoreService() {
        db = FirestoreClient.getFirestore();
    }

    public List<Task> getAllTasks() {
        ApiFuture<QuerySnapshot> future = db.collection("tasks").get();
        try {
            QuerySnapshot querySnapshot = future.get();
            return querySnapshot.getDocuments().stream()
                    .map(document -> document.toObject(Task.class))
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addTask(Task task) {
        ApiFuture<WriteResult> future = db.collection("tasks").document(task.getId()).set(task);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(Task task) {
        ApiFuture<WriteResult> future = db.collection("tasks").document(task.getId()).set(task);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(String id) {
        ApiFuture<WriteResult> future = db.collection("tasks").document(id).delete();
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
