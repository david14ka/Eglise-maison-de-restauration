package com.eglisemr.ui.login;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eglisemr.config.MyApplication;
import com.eglisemr.model.User;

public class SignupViewModel extends ViewModel {

    private MutableLiveData<User> mUser = new MutableLiveData<>();

    public boolean createUser(User user){

        MyApplication.userRef().push().setValue(user).addOnCompleteListener(task -> {

        });

        return true;
    }



}