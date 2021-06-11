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

public class SignupActivity extends AppCompatActivity {
    private TextView txtSignin;
    private EditText username , password,repassword;
    private Button btnSignup;
    DBHelperLogin db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtSignin = findViewById(R.id.sign_in_text);
        username = findViewById(R.id.user_signup);
        password = findViewById(R.id.pass_signup);
        repassword = findViewById(R.id.repass_signup);
        btnSignup = findViewById(R.id.btn_signup);

        Toolbar mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Employee management");

        db = new DBHelperLogin(this);

        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this , LoginActivity.class));
                finish();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")){
                    Toast.makeText(SignupActivity.this,"Please input username or password",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (pass.equals(repass)){
                        Boolean signup= db.signup(user,pass);
                        if (signup==true){
                            Toast.makeText(SignupActivity.this,"Sign up successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }
                    }

                }
            }
        });
    }
}