package com.example.reporraadmin.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reporraadmin.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CompanySetupActivity extends AppCompatActivity {

    private TextInputEditText etCompanyName, etIndustry, etAddress, etPhone;
    private MaterialButton btnContinue;

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_setup);


//        initViews();

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        btnContinue.setOnClickListener(v -> {
            if (validateInputs()) {
                saveCompanyData();
            }
        });
    }

//    private void initViews() {
//        etCompanyName = findViewById(R.id.et_company_name);
//        etIndustry = findViewById(R.id.et_industry);
//        etAddress = findViewById(R.id.et_address);
//        etPhone = findViewById(R.id.et_phone);
//        btnContinue = findViewById(R.id.btn_continue);
//    }

    private boolean validateInputs() {
        if (TextUtils.isEmpty(etCompanyName.getText())) {
            etCompanyName.setError("Company name is required");
            return false;
        }

        if (TextUtils.isEmpty(etIndustry.getText())) {
            etIndustry.setError("Industry is required");
            return false;
        }

        return true;
    }

    private void saveCompanyData() {
        String companyName = etCompanyName.getText().toString().trim();
        String industry = etIndustry.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        String uid = auth.getCurrentUser().getUid();

        Map<String, Object> companyMap = new HashMap<>();
        companyMap.put("companyName", companyName);
        companyMap.put("industry", industry);
        companyMap.put("address", address);
        companyMap.put("phone", phone);
        companyMap.put("adminId", uid);
        companyMap.put("createdAt", System.currentTimeMillis());

        firestore.collection("companies")
                .document(uid)
                .set(companyMap)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Company setup completed!", Toast.LENGTH_SHORT).show();
                        // Replace with your next activity
                        startActivity(new Intent(CompanySetupActivity.this, DashboardActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Failed to save company info", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}