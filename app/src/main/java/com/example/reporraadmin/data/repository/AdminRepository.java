package com.example.reporraadmin.data.repository;

import com.example.reporraadmin.data.model.Admin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminRepository {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    public interface OnAdminFetchListener {
        void onSuccess(Admin admin);
        void onFailure(String error);
    }

    public void fetchAdminInfo(OnAdminFetchListener listener) {
        String uid = auth.getCurrentUser().getUid();
        db.collection("admins").document(uid)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Admin admin = documentSnapshot.toObject(Admin.class);
                        listener.onSuccess(admin);
                    } else {
                        listener.onFailure("Admin data not found.");
                    }
                })
                .addOnFailureListener(e -> listener.onFailure(e.getMessage()));
    }
}