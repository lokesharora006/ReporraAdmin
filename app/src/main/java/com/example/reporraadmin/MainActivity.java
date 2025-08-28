package com.example.reporraadmin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.reporraadmin.view.activities.AdminInfoActivity;
import com.example.reporraadmin.view.activities.CompanySetupActivity;
import com.example.reporraadmin.view.activities.DashboardActivity;
import com.example.reporraadmin.view.activities.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();

        // Splash delay (1 second)
        new Handler().postDelayed(() -> {
            FirebaseUser currentUser = auth.getCurrentUser();

            if (currentUser != null) {
                // User is already logged in → go to Dashboard
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
            } else {
                // User not logged in → go to Login
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }

            finish(); // Finish MainActivity so user can't return here
        }, 1000); // 1000ms = 1 second
    }
}