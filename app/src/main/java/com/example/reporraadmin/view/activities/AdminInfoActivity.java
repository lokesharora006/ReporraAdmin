package com.example.reporraadmin.view.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reporraadmin.R;
import com.example.reporraadmin.data.model.Admin;
import com.example.reporraadmin.presenter.AdminInfoPresenter;
import com.example.reporraadmin.view.contracts.AdminInfoContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdminInfoActivity extends AppCompatActivity implements AdminInfoContract.View {
    private TextView fullNameTv, companyNameTv, emailTv;
    private AdminInfoPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_info);
//        fullNameTv = findViewById(R.id.et_admin_name);
//        companyNameTv = findViewById(R.id.et_company_name_admin);
        emailTv = findViewById(R.id.et_admin_email);

        presenter = new AdminInfoPresenter(this);
        presenter.getAdminInfo();
    }

    @Override
    public void showAdminInfo(Admin admin) {
        fullNameTv.setText(admin.getFullName());
        companyNameTv.setText(admin.getCompanyName());
        emailTv.setText(admin.getEmail());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}

