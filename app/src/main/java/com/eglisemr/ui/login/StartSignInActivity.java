package com.eglisemr.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eglisemr.LoginSignUpActivity;
import com.eglisemr.R;

public class StartSignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_signin_activity);

        Button emailSignInButton = findViewById(R.id.email_signin_button);
        Button googleSignInButton = findViewById(R.id.google_signin_button);

        emailSignInButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LoginSignUpActivity.class));
        });

        googleSignInButton.setOnClickListener(v -> {
            Toast.makeText(this, "Update the app!", Toast.LENGTH_SHORT).show();
        });
    }
}
