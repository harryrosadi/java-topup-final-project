package com.example.finalprojectproduct.service;

import com.example.finalprojectproduct.model.Billing;
import com.example.finalprojectproduct.model.Pascabayar;
import com.google.gson.Gson;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.TimeoutException;


@SpringBootApplication(scanBasePackages = "com.spring.beans")
public class ServiceBilling {

    static SqlSession session = null;

    public static void conn() throws IOException {
        Reader reader = Resources.getResourceAsReader("sqlmap.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }


    // ---------------------------------  MAIN  --------------------------------------- //

    // UPDATE STATUS BILLING //
    public void updateBilling(String bill) throws IOException, TimeoutException {
        conn();
        Billing data = new Gson().fromJson(bill, Billing.class);
        session.update("billing.update-status", data);
        session.commit();
        session.close();
    }

    // READ BILLING BY CUSTOMER ID AND STATUS  //
    public List<Billing> billingCustomer(int id) throws Exception {
        conn();
        List<Billing> billings = session.selectList("billing.customer-billing", id);
        System.out.println("Records Read Successfully ");
        session.commit();
        session.close();
        return billings;
    }


    // ---------------------------------  UTILITY    --------------------------------------- //

    //Create a new BILLING TO DATABASE //
    public void insertBilling(String bill) throws IOException {
        conn();
        Billing data = new Gson().fromJson(bill, Billing.class);
        //Insert BILLING
        session.insert("billing.insert", data);
        System.out.println("BILLING SUCCESSFULLY INSERTED");
        session.commit();
        session.close();
    }

    // READ BILLING BY CUSTOMER ID AND STATUS  //
    public List<Billing> billingStatus(String stat) throws Exception {
        conn();
        List<Billing> status = session.selectList("billing.billing-status", stat);
        System.out.println("Records Read Successfully ");
        session.commit();
        session.close();
        return status;
    }

    // ALL BILLING HISTORY //
    public List<Billing> billingHistory() throws IOException {
        conn();
        //select contact all contacts
        List<Billing> billings = session.selectList("billing.getAll");
        System.out.println("Records Read Successfully ");
        session.commit();
        session.close();
        return billings;
    }
}
