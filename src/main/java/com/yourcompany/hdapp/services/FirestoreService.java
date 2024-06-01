package main.java.com.yourcompany.hdapp.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.yourcompany.hdapp.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirestoreService {
    private Firestore firestore;

    public FirestoreService(Firestore firestore) {
        this.firestore = firestore;
    }

    public List<Task> getTasks() throws ExecutionException, InterruptedException {
        List<Task> tasks = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = firestore.collection("tasks").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            tasks.add(document.toObject(Task.class));
        }
        return tasks;
    }

    public void addTask(Task task) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = firestore.collection("tasks").document(task.getId()).set(task);
        future.get();
    }

    public void deleteTask(String taskId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = firestore.collection("tasks").document(taskId).delete();
        future.get();
    }
}
