package com.example.nguyenhuuhieu_appqlnv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nguyenhuuhieu_appqlnv.DatabaseHelper.DBHelperUser;
import com.example.nguyenhuuhieu_appqlnv.adapter.EmployeeAdapter;
import com.example.nguyenhuuhieu_appqlnv.model.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private FloatingActionButton btnfloat;
    private RecyclerView  recyclerView;
    private DBHelperUser db;
    EmployeeAdapter adapter;
    Button btnEdit;
    MaterialSearchBar materialSearchBar;
    List<Employee> lst=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Employee management");

        btnfloat = findViewById(R.id.btnfloat);

        recyclerView =findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter=new EmployeeAdapter();

        db = new DBHelperUser(this);
        materialSearchBar = findViewById(R.id.searchbar);
        materialSearchBar.setHint("Search");

        materialSearchBar.inflateMenu(R.menu.menu);
        materialSearchBar.getMenu().setOnMenuItemClickListener(this);

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    adapter=new EmployeeAdapter(getBaseContext(),db.getAll());
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
//        adapter = new EmployeeAdapter(this,db.getAll());

        showAll();

        btnfloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this , AddActivity.class);
               startActivity(intent);
            }
        });
    }

    private void startSearch(String text) {
        adapter = new EmployeeAdapter(this,db.search(text));
        recyclerView.setAdapter(adapter);
    }


    private void showAll(){
        List<Employee> list = db.getAll();
        adapter.setList(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        showAll();
        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mEdit:
                Intent intent = new Intent(this, EditActivity.class);
                startActivity(intent);
                break;
            case R.id.mClose:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                break;
        }
        return false;
    }
}