package com.example.reporraadmin.presenter;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.reporraadmin.data.model.Admin;
import com.example.reporraadmin.data.repository.AdminRepository;
import com.example.reporraadmin.view.contracts.AdminInfoContract;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdminInfoPresenter implements AdminInfoContract.Presenter {

    private final AdminInfoContract.View view;
    private final AdminRepository repository;

    public AdminInfoPresenter(AdminInfoContract.View view) {
        this.view = view;
        this.repository = new AdminRepository();
    }

    @Override
    public void getAdminInfo() {
        repository.fetchAdminInfo(new AdminRepository.OnAdminFetchListener() {
            @Override
            public void onSuccess(Admin admin) {
                view.showAdminInfo(admin);
            }

            @Override
            public void onFailure(String error) {
                view.showError(error);
            }
        });
    }
}