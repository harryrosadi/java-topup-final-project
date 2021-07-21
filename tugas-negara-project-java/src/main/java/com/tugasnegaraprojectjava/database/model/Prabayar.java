package com.tugasnegaraprojectjava.database.model;

public class Prabayar {

    private long id;
    private String product_name;
    private String paket_a;
    private int harga_paket_a;
    private String paket_b;
    private int harga_paket_b;
    private String paket_c;
    private int harga_paket_c;

    @Override
    public String toString() {
        return "Pascabayar{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", paket_a='" + paket_a + '\'' +
                ", harga_paket_a=" + harga_paket_a +
                ", paket_b='" + paket_b + '\'' +
                ", harga_paket_b=" + harga_paket_b +
                ", paket_c='" + paket_c + '\'' +
                ", harga_paket_c=" + harga_paket_c +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPaket_a() {
        return paket_a;
    }

    public void setPaket_a(String paket_a) {
        this.paket_a = paket_a;
    }

    public int getHarga_paket_a() {
        return harga_paket_a;
    }

    public void setHarga_paket_a(int harga_paket_a) {
        this.harga_paket_a = harga_paket_a;
    }

    public String getPaket_b() {
        return paket_b;
    }

    public void setPaket_b(String paket_b) {
        this.paket_b = paket_b;
    }

    public int getHarga_paket_b() {
        return harga_paket_b;
    }

    public void setHarga_paket_b(int harga_paket_b) {
        this.harga_paket_b = harga_paket_b;
    }

    public String getPaket_c() {
        return paket_c;
    }

    public void setPaket_c(String paket_c) {
        this.paket_c = paket_c;
    }

    public int getHarga_paket_c() {
        return harga_paket_c;
    }

    public void setHarga_paket_c(int harga_paket_c) {
        this.harga_paket_c = harga_paket_c;
    }
}
