package com.example.finalprojectproduct.service;


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

@SpringBootApplication(scanBasePackages = "com.spring.beans")
public class ServicePascabayar {

    static SqlSession session = null;

    public static void conn() throws IOException {
        Reader reader = Resources.getResourceAsReader("sqlmap.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }

    // CREATE NEW PRODUCT //
    public void insertProduct(String product) throws IOException {
        conn();
//Create a new PRODUCT TO DATABASE
        Pascabayar dataProduct = new Gson().fromJson(product, Pascabayar.class);
        //Insert PRODUCT
        session.insert("pascabayar.insert", dataProduct);
        System.out.println("PRODUCT SUCCESSFULLY INSERTED");
        session.commit();
        session.close();
    }

    // READ ALL PRODUCT //
    public List<Pascabayar> readall() throws IOException {
        conn();
        //select contact all contacts
        List<Pascabayar> data = session.selectList("pascabayar.getAll");
        System.out.println("Records Read Successfully ");
        session.commit();
        session.close();
        return data;
    }

// READ BY PRODUCT NAME //
    public List<Pascabayar> readByName(String name) throws Exception {
        conn();
        // GET PRODCUT BY NAME //
        List<Pascabayar> product = session.selectList("pascabayar.view-product", name);
        System.out.println("Records Read Successfully ");
        session.commit();
        session.close();
        return product;
    }

}
