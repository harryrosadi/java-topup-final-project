package com.example.finalprojectproduct.service;

import com.example.finalprojectproduct.model.CustomerData;
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
public class ServiceCustomer {

    static SqlSession session = null;

    public static void conn() throws IOException {
        Reader reader = Resources.getResourceAsReader("sqlmap.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }

    public void insertNewCustomer(String cust) throws IOException {
        conn();
//Create a new PRODUCT TO DATABASE
        CustomerData data = new Gson().fromJson(cust, CustomerData.class);
        //Insert NEW CUSTOMER
        session.insert("customer.insert", data);
        System.out.println("NEW CUSTOMER SUCCESSFULLY INSERTED");
        session.commit();
        session.close();
    }

    // ALL CUSTOMER DATA //
    public List<CustomerData> allData() throws IOException {
        conn();
        //select contact all contacts
        List<CustomerData> data = session.selectList("billing.getall");
        System.out.println("Records Read Successfully ");
        session.commit();
        session.close();
        return data;
    }
}
