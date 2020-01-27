package org.cxhubnigeria.rfidlibrary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword, matid, studname, dept, school, phone;     //hit option + enter if you on mac , for windows hit ctrl + enter
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference("libraryusers");

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        matid = (EditText) findViewById(R.id.mat_id);
        studname = (EditText) findViewById(R.id.stud_name);
        dept = (EditText) findViewById(R.id.stud_dept);
        school = (EditText) findViewById(R.id.stud_school);
        phone = (EditText) findViewById(R.id.phone);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, ResetActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String matric = matid.getText().toString().trim();
                String sName = studname.getText().toString().trim();
                String deptment = dept.getText().toString().trim();
                String faculty = school.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String number = phone.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(matric)) {
                    Toast.makeText(getApplicationContext(), "Enter Matric Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(sName)) {
                    Toast.makeText(getApplicationContext(), "Enter your Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(deptment)) {
                    Toast.makeText(getApplicationContext(), "Enter your Department!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(faculty)) {
                    Toast.makeText(getApplicationContext(), "Enter your School!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(getApplicationContext(), "Enter your phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                final DatabaseReference post = mRef.push();
                post.child("matId").setValue(matric);
                post.child("Studname").setValue(sName);
                post.child("dept").setValue(deptment);
                post.child("school").setValue(faculty);
                post.child("emailname").setValue(email);
                post.child("password").setValue(password);
                post.child("phone").setValue(number);

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}

