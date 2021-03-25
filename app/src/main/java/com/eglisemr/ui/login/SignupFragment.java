package com.eglisemr.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.eglisemr.R;
import com.eglisemr.model.User;

/**
 * A placeholder fragment containing a simple view.
 */
public class SignupFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public String ARG_SECTION_TITLE = "Sign up";

    private SignupViewModel signupViewModel;
    private User user;

    public static SignupFragment newInstance(int index) {
        SignupFragment fragment = new SignupFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signup, container, false);

       /* EditText textEmail = root.findViewById(R.id.email);
        EditText textPassword = root.findViewById(R.id.password);
        EditText textFirstname = root.findViewById(R.id.firstname);
        EditText textLastname = root.findViewById(R.id.lastname);*/

        Button signButton = root.findViewById(R.id.login_button);
        return root;
    }
}