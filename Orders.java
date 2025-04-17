package com.example.fuelondemand;

public class Orders {
    public Orders() {
    }

    String name,mobile,location,fueltype,quantity,payment_method;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Orders(String name, String mobile,String location,String fueltype,String quantity,String payment_method) {
        this.name = name;
        this.mobile = mobile;
        this.location = location;
        this.fueltype = fueltype;
        this.quantity = quantity;
        this.payment_method = payment_method;
    }
}
