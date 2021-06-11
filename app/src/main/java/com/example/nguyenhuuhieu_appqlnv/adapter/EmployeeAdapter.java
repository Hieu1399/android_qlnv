package com.example.nguyenhuuhieu_appqlnv.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nguyenhuuhieu_appqlnv.R;
import com.example.nguyenhuuhieu_appqlnv.model.Employee;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{
    private List<Employee> list;
    private Context context;


    public EmployeeAdapter(Context context,List<Employee> list) {
        this.list = list;
        this.context=context;

    }

    public EmployeeAdapter(){
        list=new ArrayList<>();
    }
    public void setList(List<Employee> list) {
        this.list = list;
    }
    public List<Employee> getList() {
        return list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.employee_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        Employee e = list.get(position);

        holder.txtname.setText(e.getId() + "-Name: " + e.getName());
        if (e.isGender()) {
            holder.gender.setImageResource(R.drawable.icon_male);
        } else {
            holder.gender.setImageResource(R.drawable.icon_female);
        }
        holder.txtdob.setText("DoB: " + e.getDate());
        holder.txtphone.setText("Phone: " + e.getPhone());
        holder.txtadd.setText("Address: " + e.getAddress());
        holder.txthsl.setText("Hsl: " + e.getHsl());



    }
    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtid;
        private TextView txtname;
        private ImageView gender;
        private TextView txtdob;
        private TextView txtphone;
        private TextView txtadd;
        private TextView txthsl;

        public ViewHolder(View v) {
            super(v);
            txtid=v.findViewById(R.id.listid);
            txtname =v.findViewById(R.id.listname);
            gender  =v.findViewById(R.id.imggender);
            txtdob =v.findViewById(R.id.listdob);
            txtphone =v.findViewById(R.id.listphone);
            txtadd =v.findViewById(R.id.listadd);
            txthsl =v.findViewById(R.id.listhsl);

        }
    }
}
