package com.example.nguyenhuuhieu_appqlnv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.nguyenhuuhieu_appqlnv.DatabaseHelper.DBHelperUser;
import com.example.nguyenhuuhieu_appqlnv.model.Employee;

public class EditActivity extends AppCompatActivity {
    private EditText edName,eddoB,edPhone,edAddress,edhsl,edId;
    private RadioButton female,male;
    private Button btngetid, btnUpdate,btnDele;
    long id;
    DBHelperUser db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Employee management");

        edId = findViewById(R.id.ed_id);
        edName =findViewById(R.id.ed_name);
        male=findViewById(R.id.ed_male);
        female = findViewById(R.id.ed_female);
        eddoB = findViewById(R.id.ed_doB);
        edPhone = findViewById(R.id.ed_phone);
        edAddress = findViewById(R.id.ed_address);
        edhsl = findViewById(R.id.ed_hsl);

        btngetid = findViewById(R.id.btngetId);
        btnUpdate = findViewById(R.id.btn_update);
        btnDele=findViewById(R.id.btn_delete);
        btnUpdate.setEnabled(false);
        btnDele.setEnabled(false);

        db=new DBHelperUser(this);

        btngetid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id =Integer.parseInt(edId.getText().toString());
                Employee e =db.getById(id);
                if(e==null){
                    Toast.makeText(getApplicationContext(),"ID doesn't exist!",Toast.LENGTH_SHORT).show();
                }else {
                    edName.setText(e.getName());
                    if(e.isGender()){
                        male.setChecked(true);
                    }else {
                        female.setChecked(true);
                    }
                    eddoB.setText(e.getDate());
                    edPhone.setText(""+e.getPhone());
                    edAddress.setText(e.getAddress());
                    edhsl.setText(""+e.getHsl());
                    edId.setEnabled(false);
                    btnUpdate.setEnabled(true);
                    btnDele.setEnabled(true);
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edId.getText().toString());
                String name = edName.getText().toString();
                boolean gender = false;
                if(male.isChecked()){
                    gender=true;
                }
                String dob = eddoB.getText().toString();
                int phone = Integer.parseInt(edPhone.getText().toString());
                String address = edAddress.getText().toString();
                int hsl = Integer.parseInt(edhsl.getText().toString());
                int changeRow = db.update(new Employee(id,name,gender,dob,phone,address,hsl));
                if(changeRow>0){
                    Toast.makeText(EditActivity.this,"Update Successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EditActivity.this,"Update Failed",Toast.LENGTH_SHORT).show();
                }
                edId.setEnabled(true);
                btnUpdate.setEnabled(false);
                btnDele.setEnabled(false);
            }
        });

        btnDele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Integer.parseInt(edId.getText().toString());
                int dele= db.delete(id);
                if(dele>0){
                    Toast.makeText(EditActivity.this,"Delete Successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EditActivity.this,"Deleted Failed",Toast.LENGTH_SHORT).show();
                }
                btnDele.setEnabled(false);
                btnUpdate.setEnabled(false);
                edId.setEnabled(true);
            }
        });


    }


}