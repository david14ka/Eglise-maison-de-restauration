package com.eglisemr.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.eglisemr.R;
import com.eglisemr.config.MyApplication;
import com.eglisemr.model.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public String ARG_SECTION_TITLE = "Login in";

    private LoginViewModel loginViewModel;

    public static LoginFragment newInstance(int index) {
        LoginFragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        TextInputLayout textEmail = root.findViewById(R.id.textEmail);
        TextInputLayout textPassword = root.findViewById(R.id.textPassword);
        Button signButton = root.findViewById(R.id.login_button);

        signButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), textEmail.getEditText().getText().toString() + " " + textPassword.getEditText().getText().toString(), Toast.LENGTH_SHORT).show();
        });
        return root;
    }
}