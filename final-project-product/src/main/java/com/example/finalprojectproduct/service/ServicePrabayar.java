package com.example.finalprojectproduct.service;


import com.example.finalprojectproduct.model.Pascabayar;
import com.example.finalprojectproduct.model.Prabayar;
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
public class ServicePrabayar {

    static SqlSession session = null;
    Pascabayar pascabayar = new Pascabayar();

    public static void conn() throws IOException {
        Reader reader = Resources.getResourceAsReader("sqlmap.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }

    public void insertProduct(String product) throws IOException {
        conn();
//Create a new PRODUCT TO DATABASE
        Prabayar dataProduct = new Gson().fromJson(product, Prabayar.class);
        //Insert PRODUCT
        session.insert("prabayar.insert", dataProduct);
        System.out.println("PRODUCT SUCCESSFULLY INSERTED");
        session.commit();
        session.close();
    }

    public List<Prabayar> readall() throws IOException {
        conn();
        //select contact all contacts
        List<Prabayar> product = session.selectList("prabayar.getAll");
        System.out.println("Records Read Successfully ");
        session.commit();
        session.close();
        return product;
    }

// READ BY PRODUCT NAME //
    public List<Prabayar> readByName(String name) throws Exception {
        conn();
        //select contact all by product name
        List<Prabayar> product = session.selectList("prabayar.view-product", name);
        System.out.println("Records Read Successfully ");
        session.commit();
        session.close();
        return product;
    }

}
