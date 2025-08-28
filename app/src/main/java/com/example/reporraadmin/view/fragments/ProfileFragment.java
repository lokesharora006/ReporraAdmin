package com.example.reporraadmin.view.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.reporraadmin.R;
import com.example.reporraadmin.view.activities.LoginActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        MaterialButton btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> {
            // Show custom logout dialog when user clicks logout button
            showCustomLogoutDialog();
        });

        return view;
    }

    // ✅ Move this method OUTSIDE onCreateView()
    private void showCustomLogoutDialog() {
        View dialogView = LayoutInflater.from(requireContext())
                .inflate(R.layout.dialog_logout, null);

        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .setCancelable(false)
                .create();

        Button btnYes = dialogView.findViewById(R.id.btnYes);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        btnYes.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            // Clear session if using SharedPreferences
            requireActivity().getSharedPreferences("user_session", Context.MODE_PRIVATE)
                    .edit().clear().apply();

            Intent intent = new Intent(requireActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();

            dialog.dismiss();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
