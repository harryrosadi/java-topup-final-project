package com.example.finalprojectproduct.service;

import com.example.finalprojectproduct.model.Payment;
import com.google.gson.Gson;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.Reader;
import java.util.List;


@SpringBootApplication(scanBasePackages = "com.spring.beans")
public class ServicePayment {
    static SqlSession session = null;

    public static void conn() throws IOException {
        Reader reader = Resources.getResourceAsReader("sqlmap.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }

    // INSERT PAYMENT //
    public void insertPayment(String product) throws IOException {
        conn();
//Create a new PRODUCT TO DATABASE
        Payment dataProduct = new Gson().fromJson(product, Payment.class);
        //Insert PRODUCT
        session.insert("payment.insert", dataProduct);
        System.out.println("PAYMENT PENDING");
        session.commit();
        session.close();
    }

    // PAYMENT PASCABAYAR //
    public String pascabayarOrderId(int id) throws IOException {
        conn();
        String status = null;

        Payment pascabayar = session.selectOne("payment.check-order-id", id);
        if (pascabayar != null){
            status = pascabayar.getStatus();
            session.commit();
            session.close();
            return status;
        } else {
            session.commit();
            session.close();
            return status = null;
        }
    }

    // PAYMENT PRABAYAR //
    public String prabayarOrderId(int id) throws IOException {
        conn();
        String status = null;

        Payment prabayar = session.selectOne("payment.check-order-id", id);
        if (prabayar != null){
            status = prabayar.getStatus();
            session.commit();
            session.close();
            return status;
        } else {
            session.commit();
            session.close();
            return status = null;
        }
    }

    // PAYMENT DELETE //
    public void deleteOrder(int oderid) throws IOException {
        conn();
        session.delete("payment.delete-order-id", oderid);
        System.out.println("PAYMENT FAILED");
        session.commit();
        session.close();

    }


    // ................................... UTILITY ................................... //

    // READ ALL PAYMENT //
    public List<Payment> readallPayment() throws IOException {
        conn();
        //select contact all contacts
        List<Payment> data = session.selectList("payment.getAll");
        System.out.println("Records Read Successfully ");
        session.commit();
        session.close();
        return data;
    }

    // READ BY PRODUCT NAME //
    public List<Payment> paymentProductName(String name) throws Exception {
        conn();
        // GET PRODCUT BY NAME //
        List<Payment> product = session.selectList("payment.view-product", name);
        System.out.println("Records Read Successfully ");
        session.commit();
        session.close();
        return product;
    }

}
