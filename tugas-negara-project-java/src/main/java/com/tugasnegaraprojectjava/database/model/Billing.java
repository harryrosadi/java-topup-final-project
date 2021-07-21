package com.tugasnegaraprojectjava.database.model;

public class Billing {

    private int customer_id;
    private String name;
    private String email;
    private String product_name;
    private String month;
    private int year;
    private int billing;
    private String status;


    @Override
    public String toString() {
        return "Billing{" +
                "customer_id=" + customer_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", product_name='" + product_name + '\'' +
                ", month='" + month + '\'' +
                ", year=" + year +
                ", billing=" + billing +
                ", status='" + status + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBilling() {
        return billing;
    }

    public void setBilling(int billing) {
        this.billing = billing;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
