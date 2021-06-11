package com.example.nguyenhuuhieu_appqlnv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.nguyenhuuhieu_appqlnv.DatabaseHelper.DBHelperUser;
import com.example.nguyenhuuhieu_appqlnv.model.Employee;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private EditText edName,eddoB,edPhone,edAddress,edhsl;
    private Button btnAdd,btnUpdate;
    private RadioButton female,male;
    private DBHelperUser db;
    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Employee management");

        edName =findViewById(R.id.name);
        male=findViewById(R.id.male);
        female = findViewById(R.id.female);
        eddoB = findViewById(R.id.doB);
        edPhone = findViewById(R.id.phone);
        edAddress = findViewById(R.id.address);
        edhsl = findViewById(R.id.hsl);

        btnAdd = findViewById(R.id.btn_addnew);

        db=new DBHelperUser(this);

        eddoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                boolean gender=false;
                if(male.isChecked()){
                    gender=true;
                }
                String dob = eddoB.getText().toString();
                int phone = Integer.parseInt(edPhone.getText().toString());
                String address = edAddress.getText().toString();
                int hsl = Integer.parseInt(edhsl.getText().toString());
                try {
                    Employee em = new Employee(name,gender,dob,phone,address,hsl);
                    db.addEmploy(em);
                    Toast.makeText(AddActivity.this, "Add Successfully ", Toast.LENGTH_SHORT).show();
                }catch (NumberFormatException e){
                    System.out.println(e);
                }
                startActivity(new Intent(AddActivity.this,MainActivity.class));
            }
        });

    }

    private void ChonNgay(){

        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                eddoB.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

}