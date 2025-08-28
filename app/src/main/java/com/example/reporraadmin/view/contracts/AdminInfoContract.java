package com.example.reporraadmin.view.contracts;

import com.example.reporraadmin.data.model.Admin;

public interface AdminInfoContract {
    interface View {
        void showAdminInfo(Admin admin);
        void showError(String error);
    }

    interface Presenter {
        void getAdminInfo();
    }
}
