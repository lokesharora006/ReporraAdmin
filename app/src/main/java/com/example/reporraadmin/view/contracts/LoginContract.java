package com.example.reporraadmin.view.contracts;

public interface LoginContract {

    interface View {
        void showProgress();

        void hideProgress();

        void onLoginSuccess();

        void onLoginFailure(String message);

        void showEmailError(String message);

        void showPasswordError(String message);
    }

    interface Presenter {
        void login(String email, String password);
    }
}


