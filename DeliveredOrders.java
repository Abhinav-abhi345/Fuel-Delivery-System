package com.example.deliveryboy;

public class DeliveredOrders {
    String name,mobile,location,fueltype,quantity,delivery_status;

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

    public String getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(String delivery_status) {
        this.delivery_status = delivery_status;
    }

    public DeliveredOrders(String name, String mobile,String location, String fueltype, String quantity, String delivery_status) {
        this.name = name;
        this.mobile = mobile;
        this.location = location;
        this.fueltype = fueltype;
        this.quantity = quantity;
        this.delivery_status = delivery_status;
    }

    public DeliveredOrders() {
    }
}
