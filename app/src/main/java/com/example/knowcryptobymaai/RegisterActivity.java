package com.example.knowcryptobymaai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    public EditText inputEmail,inputPassword;
    public TextView ShowLogin;
    public Button btnSignup;
    public ProgressBar progressBar;
    //ประกาศเพื่อเรียกใช้ Authentication ของ Firebase
    public FirebaseAuth registerAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //findviewbyid
        inputEmail = (EditText)findViewById(R.id.edtEmail);
        inputPassword = (EditText)findViewById(R.id.edtPassword);
        ShowLogin = (TextView) findViewById(R.id.txtLogin);
        btnSignup = (Button) findViewById(R.id.btnRegister);
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
//เรียกใช้ Authentication การกาหนดสิทธิ์ผู้ใช้งานของ Firebase
        registerAuth = FirebaseAuth.getInstance();
    }

    public void Signup(View view){
//รับค่าที่ผู้ใช้อีเมล์ รหัสผ่าน
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
//ตรวจสอบว่ากรอกอีเมลื กรอกรหัสผ่าน และกรอกรหัสผ่านเกิน 6 ตัวไหม
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
//สร้างผู้ใช้ใหม่
        registerAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegisterActivity.this, "Complete Register User",
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Error" + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Intent open = new Intent (RegisterActivity.this,MainActivity.class);
                            startActivity(open);
                            finish();
                        }//ปิด if
                    }
                }); }//ปิด method


    public void login (View view){
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }


}//ปิด class