package com.example.nguyenhuuhieu_appqlnv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenhuuhieu_appqlnv.DatabaseHelper.DBHelperLogin;

public class LoginActivity extends AppCompatActivity {
    private EditText username , password;
    private TextView txtLogin;
    private Button btnLogin;
    DBHelperLogin db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Employee management");

        txtLogin = findViewById(R.id.signup_text);
        username = findViewById(R.id.user_login);
        password = findViewById(R.id.pass_login);
        btnLogin = findViewById(R.id.btn_login);
        db = new DBHelperLogin(this);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this ,SignupActivity.class));
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(LoginActivity.this,"Please input username or password",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checklogin=db.checkLogin(user,pass);
                    if (checklogin==true){
                        Toast.makeText(LoginActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"Wrong username or password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}