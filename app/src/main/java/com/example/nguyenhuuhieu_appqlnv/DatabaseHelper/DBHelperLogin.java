package com.example.nguyenhuuhieu_appqlnv.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperLogin extends SQLiteOpenHelper {
    public static final String DB_NAME = "Login.db";

    public DBHelperLogin(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public boolean signup(String username,String password){
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = sql.insert("users",null,contentValues);
        if (result==-1) return false;
        else return true;
    }
    public boolean checkLogin(String username,String password){
        SQLiteDatabase sql= this.getWritableDatabase();
        Cursor cursor = sql.rawQuery("Select * from users where username=? and password=?",
                new String[]{username,password});
        if (cursor.getCount()>0){
            return true;
        }else return false;
    }

}
