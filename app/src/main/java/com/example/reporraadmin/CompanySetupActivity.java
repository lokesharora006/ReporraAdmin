package com.example.reporraadmin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class CompanySetupActivity extends AppCompatActivity {

    TextInputEditText etName, etEmail, etPhone, etDesignation;
    MaterialButton btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etName = findViewById(R.id.et_admin_name);
        etEmail = findViewById(R.id.et_admin_email);
        etPhone = findViewById(R.id.et_admin_phone);
        etDesignation = findViewById(R.id.et_designation);
        btnComplete = findViewById(R.id.btn_complete);

        btnComplete.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save admin info or pass to next screen

            Toast.makeText(this, "Setup Complete", Toast.LENGTH_SHORT).show();

            // Example: Navigate to Dashboard
            Intent intent = new Intent(AdminInfoActivity.this, DashboardActivity.class);
            startActivity(intent);
        });
    }
}