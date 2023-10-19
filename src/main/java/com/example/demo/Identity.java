package com.example.demo;

public class Identity {
    public String document;
    public String name;
    public String cellphone;
    public String address;

    public Identity(String document, String name, String cellphone, String address) {
        this.document = document;
        this.name = name;
        this.cellphone = cellphone;
        this.address = address;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
