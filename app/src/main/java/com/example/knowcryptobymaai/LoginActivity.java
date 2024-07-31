package com.example.knowcryptobymaai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    public FirebaseAuth registerAuth;
    public EditText Inputemail,Inputpassword;
    public TextView ShowRegister,Repassword;
    public Button btnSignin;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerAuth = FirebaseAuth.getInstance();
        Inputemail = (EditText)findViewById(R.id.edtEmail);
        Inputpassword = (EditText)findViewById(R.id.edtPassword);
        ShowRegister = (TextView) findViewById(R.id.txtRegister);
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        Repassword = (TextView)findViewById(R.id.txtRePassword);
        btnSignin = (Button) findViewById(R.id.bntlogin);
    }

    public void login(View view){
        String email = Inputemail.getText().toString();
        String password = Inputpassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
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
        progressBar.setVisibility(View.VISIBLE);
        registerAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }//ปิด login

    public void ResetPassword(View view)
    {
        final EditText reset = new EditText(view.getContext());
        AlertDialog.Builder passwordReset = new AlertDialog.Builder(view.getContext());
        passwordReset.setTitle("Reset Password");
        passwordReset.setMessage("Enter your email to received reset link.");
        passwordReset.setView(reset);
        passwordReset.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//extract the Email and send reset link
                String mail = reset.getText().toString();
//คาสั่งสาหรับเปลี่ยนรหัสผ่าน
                registerAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(LoginActivity.this, "Reset link sent to your email.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Error! Reset link is not sent " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        passwordReset.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//close Dialog
            }
        });
        passwordReset.create().show();
    }//ปิด reset password

    public void OpenRegister (View view)
    {
        startActivity(new Intent(this,RegisterActivity.class));
        finish();
    }//

}//ปิด Class