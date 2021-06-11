package com.example.nguyenhuuhieu_appqlnv.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import androidx.annotation.Nullable;

import com.example.nguyenhuuhieu_appqlnv.model.Employee;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

public class DBHelperUser extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Employee.db";
    private static final int DATABSE_VERSION=1;

    public static final String row_id="id";
    public static final String row_name="name";
    public static final String row_gender="gender";
    public static final String row_date="date";
    public static final String row_phone="phone";
    public static final String row_address="address";
    public static final String row_hsl="hsl";



    private SQLiteDatabase db;

    public DBHelperUser(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE employee(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "gender BOOLEAN," +
                "date TEXT,"+
                "phone INTERGER," +
                "address TEXT," +
                "hsl INTERGER)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists employee");
    }


    public void addEmploy(Employee e) {
        String sql = "INSERT INTO employee(name,gender,date,phone,address,hsl) VALUES(?,?,?,?,?,?)";
        String[] args = {e.getName(),
                Boolean.toString(e.isGender()),
                e.getDate(),
                Integer.toString(e.getPhone()),
                e.getAddress(),
                Integer.toString(e.getHsl())
        };
        SQLiteDatabase statment = getWritableDatabase();
        statment.execSQL(sql, args);
    }

    // get all
    public List<Employee> getAll(){
        List<Employee> list=new ArrayList<>();
        SQLiteDatabase statement=getReadableDatabase();
        Cursor rs=statement.query("employee",null,
                null,null,null,
                null,null);
        while((rs!=null && rs.moveToNext())){
            int id=rs.getInt(0);
            String name=rs.getString(1);
            boolean gender= rs.getString(2).equals("true");
            String date = rs.getString(3);
            int phone=rs.getInt(4);
            String address=rs.getString(5);
            int hsl=rs.getInt(6);
            list.add(new Employee(id,name,gender,date,phone,address,hsl));
        }
        return list;
    }

    //getBy id
    public Employee getById(int id){
        String whereClause="id =?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("employee",null,whereClause,
                whereArgs,null,null,null);
        if(rs.moveToNext()){
            String name=rs.getString(1);
            boolean gender= rs.getString(2).equals("true");
            String date = rs.getString(3);
            int phone=rs.getInt(4);
            String address=rs.getString(5);
            int hsl=rs.getInt(6);
            rs.close();
            return new Employee(id,name,gender,date,phone,address,hsl);
        }
        return null;
    }

    //update
    public int update(Employee e){
        ContentValues v=new ContentValues();
        v.put("name",e.getName());
        v.put("gender",String.valueOf(e.isGender()));
        v.put("date",e.getDate());
        v.put("phone",e.getPhone());
        v.put("address",e.getAddress());
        v.put("hsl",e.getHsl());
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(e.getId())};
        SQLiteDatabase st=getWritableDatabase();
        return st.update("employee",v,whereClause,whereArgs);
    }


    //delete
    public int delete(int id){
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("employee",whereClause,whereArgs);
    }

    //search
    public List<Employee>  search(String name)  {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlselect={row_id,row_name,row_gender,row_date,row_phone,row_address,row_hsl};
        String table = "employee";
        qb.setTables(table);

        Cursor cursor = qb.query(db,sqlselect,row_name+" LIKE ?",
                new String[]{"%"+name+"%"},null,null,null);
        List<Employee>result=new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                Employee e= new Employee();
                e.setId(cursor.getInt(cursor.getColumnIndex(row_id)));
                e.setName(cursor.getString(cursor.getColumnIndex(row_name)));
                e.setGender(cursor.getString(cursor.getColumnIndex(row_gender)).equals("true"));
                e.setDate(cursor.getString(cursor.getColumnIndex(row_date)));
                e.setPhone(cursor.getInt(cursor.getColumnIndex(row_phone)));
                e.setAddress(cursor.getString(cursor.getColumnIndex(row_address)));
                e.setHsl(cursor.getInt(cursor.getColumnIndex(row_hsl)));
                result.add(e);
            }while (cursor.moveToNext());
        }
        return result;
    }

    //getsearchname
    public List<Employee>  getName(String name)  {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlselect={row_name};
        String table = "employee";
        qb.setTables(table);

        Cursor cursor = qb.query(db,sqlselect,null,
                null,null,null,null);
        List<Employee>result=new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                Employee e= new Employee();
                e.setId(cursor.getInt(cursor.getColumnIndex(row_id)));
                e.setGender(cursor.getString(cursor.getColumnIndex(row_gender)).equals("true"));
                e.setDate(cursor.getString(cursor.getColumnIndex(row_date)));
                e.setPhone(cursor.getInt(cursor.getColumnIndex(row_phone)));
                e.setAddress(cursor.getString(cursor.getColumnIndex(row_address)));
                e.setHsl(cursor.getInt(cursor.getColumnIndex(row_hsl)));
                result.add(e);
            }while (cursor.moveToNext());
        }
        return result;
    }

}
