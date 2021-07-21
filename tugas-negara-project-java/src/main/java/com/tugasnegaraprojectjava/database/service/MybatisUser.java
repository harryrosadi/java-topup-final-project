package com.tugasnegaraprojectjava.database.service;

import com.google.gson.Gson;
import com.tugasnegaraprojectjava.database.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MybatisUser {
    static SqlSession session = null;
    User user = new User();

    public static void conn() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMap.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
    }


    //Create a new register user
    public User registerUser(String email, User users) throws IOException {
        conn();
        User user = (User) session.selectOne("User.user-regis", email);
        if (user == null) {
            session.insert("User.insert", users);
            System.out.println("register Successfully ");
            session.commit();
            session.close();
            return users;
        } else {
            session.commit();
            session.close();
            return users = null;
        }
    }

    // LOGIN USER //
    public User loginUser(String email, String pass) throws IOException {
        conn();
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("password", pass);
        User user = (User) session.selectOne("User.user-login", map);
        if (user != null) {
            System.out.println("login Successfully ");
            session.commit();
            session.close();
            return user;
        } else {
            session.commit();
            session.close();
            return user = null;
        }
    }

    public User findByEmail(String email) throws IOException {
        conn();
        User user = (User) session.selectOne("User.find-email", email);
        session.commit();
        session.close();

        return user;

    }

    public User findByToken(String token) throws IOException {
        conn();
        User user = (User) session.selectOne("User.find-token", token);
        session.commit();
        session.close();
        return user;
    }

    public void updateUser (User user) throws IOException {
        conn();
        session.update("User.update-user", user);
        session.commit();
        session.close();

    }
}
