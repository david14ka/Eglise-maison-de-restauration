package com.eglisemr.ui.login;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.eglisemr.config.MyApplication;
import com.eglisemr.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.pixplicity.easyprefs.library.Prefs;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<User> mUser = new MutableLiveData<>();

    public LiveData<User> getUser(String email, String password){

        MyApplication.userRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot shot :
                            dataSnapshot.getChildren()) {

                        User user = shot.getValue(User.class);
                        if (email.equalsIgnoreCase(user.getEmail())) {
                            if (password.equalsIgnoreCase(user.getPassword())) {

                                mUser.setValue(user);
                                Prefs.putString("email",user.getEmail());
                                Prefs.putString("firstname",user.getFirstname());
                                Prefs.putString("lastname",user.getLastname());
                                Prefs.putBoolean("admin",user.isAdmin());
                                return;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return mUser;
    }
}