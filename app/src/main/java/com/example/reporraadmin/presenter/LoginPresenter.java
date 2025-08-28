package com.example.reporraadmin.presenter;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.reporraadmin.view.contracts.LoginContract;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    private final FirebaseAuth firebaseAuth;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            view.showEmailError("Email is required");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showEmailError("Invalid email format");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            view.showPasswordError("Password is required");
            return;
        }

        view.showProgress();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    view.hideProgress();
                    if (task.isSuccessful()) {
                        view.onLoginSuccess();
                    } else {
                        String errorMsg = task.getException() != null ? task.getException().getMessage() : "Login failed";
                        view.onLoginFailure(errorMsg);
                    }
                });
    }
}