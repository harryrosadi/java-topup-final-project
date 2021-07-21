package com.tugasnegaraprojectjava.database.model;

public class PaymentTB {

    private long id;
    private int order_id;
    private String product_name;
    private String payment_method;
    private String packet;
    private int price;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPacket() {
        return packet;
    }

    public void setPacket(String packet) {
        this.packet = packet;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", product_name='" + product_name + '\'' +
                ", payment_method='" + payment_method + '\'' +
                ", packet='" + packet + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
