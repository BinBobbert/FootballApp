package com.example.football.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.football.ui.MainActivity;
import com.example.football.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    EditText mUsername, mPassword, mEmail;
    Button registerBtn;
    FirebaseAuth fAuth;
    ProgressBar progress;
    TextView gotoLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsername = findViewById(R.id.username_edit);
        mEmail = findViewById(R.id.email_edit);
        mPassword = findViewById(R.id.password_edit);
        registerBtn = findViewById(R.id.register_btn);
        progress = findViewById(R.id.register_progress);
        gotoLogin = findViewById(R.id.or_login);
        fAuth = FirebaseAuth.getInstance();


        gotoLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String username = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required.");
                    return;
                }
                if (password.length() < 6){
                    mPassword.setError("Password should be longer than 5 characters");
                }

                progress.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            FirebaseUser user = fAuth.getCurrentUser();

                            Log.d("profile", "henlo??.");

                            UserProfileChangeRequest prfofileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(mUsername.getText().toString().trim())
                                    .build();

                            user.updateProfile(prfofileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Log.d("profile", "User profile updated.");

                                    }
                                }
                            });


                            Toast.makeText(RegisterActivity.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));


                        } else {
                            Toast.makeText(RegisterActivity.this, "User creation failed ", Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.INVISIBLE);


                        }

                    }
                });




            }
        });




    }
}
