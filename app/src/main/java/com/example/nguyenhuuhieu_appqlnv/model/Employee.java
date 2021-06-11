package com.example.nguyenhuuhieu_appqlnv.model;

public class Employee {
    private int id;
    private String name;
    private boolean gender;
    private String date;
    private int phone;
    private String address;
    private int hsl;

    public Employee(){

    }
    public Employee(int id, String name, boolean gender, String date, int phone, String address, int hsl) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.phone = phone;
        this.address = address;
        this.hsl = hsl;
    }
    public Employee(String name, boolean gender, String date, int phone, String address, int hsl) {
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.phone = phone;
        this.address = address;
        this.hsl = hsl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getHsl() {
        return hsl;
    }

    public void setHsl(int hsl) {
        this.hsl = hsl;
    }
}
