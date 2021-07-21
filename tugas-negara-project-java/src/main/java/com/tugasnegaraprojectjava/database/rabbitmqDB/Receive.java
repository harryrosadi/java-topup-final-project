package com.tugasnegaraprojectjava.database.rabbitmqDB;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.tugasnegaraprojectjava.database.model.User;
import com.tugasnegaraprojectjava.database.service.MybatisUser;
import com.tugasnegaraprojectjava.database.service.UserForgotPassword;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Receive {

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private MybatisUser mybatisUser = new MybatisUser();
    private UserForgotPassword userForgotPassword = new UserForgotPassword();

    public void conn() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
    }

    // REGISTER USER TO DATABASE//
    public void register() {
        try {
            conn();
            channel = connection.createChannel();
            channel.queueDeclare("register", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + message + "'");
                try {
                    Send send = new Send();
                    User user = new Gson().fromJson(message, User.class);
                    String email = user.getEmail();
//                    String pass = user.getPassword();
                    User dataUser = mybatisUser.registerUser(email, user);
                    if (dataUser != null) {
                        send.sendtoApi(new Gson().toJson(dataUser), "message-register");
                    } else {
                        send.sendtoApi("email sudah terdaftar", "message-register");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("register", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error insert = " + e);
        }
    }

    // LOGIN USER GET FROM DATABASE//
    public void login() {
        try {
            conn();
            channel = connection.createChannel();
            channel.queueDeclare("login", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + message + "'");
                try {
                    Send send = new Send();
                    User user = new Gson().fromJson(message, User.class);
                    String email = user.getEmail();
                    String pass = user.getPassword();
                    User dataUser = mybatisUser.loginUser(email, pass);
                    if (dataUser != null) {
                        send.sendtoApi(new Gson().toJson(dataUser), "message-login");
                    } else {
                        send.sendtoApi("error", "message-login");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("login", true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("Error insert = " + e);
        }
    }

    public void forgotPassword() {
        try{
            conn();
            channel = connection.createChannel();
            channel.queueDeclare("forgot-password", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String siswaString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + siswaString + "'");
                try {
                    userForgotPassword.forgotPassword(siswaString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("forgot-password", true, deliverCallback, consumerTag -> { });
        }catch (Exception e) {
            System.out.println("Error reset password = " + e);
        }
    }

    public void resetPassword() {
        try{
            conn();
            channel = connection.createChannel();
            channel.queueDeclare("reset-password", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String siswaString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + siswaString + "'");
                try {
                    userForgotPassword.forgotPassword(siswaString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("reset-password", true, deliverCallback, consumerTag -> { });
        }catch (Exception e) {
            System.out.println("Error reset password = " + e);
        }
    }
}
