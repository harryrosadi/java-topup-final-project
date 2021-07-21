package com.tugasnegaraprojectjava.restAPI.rabbitmqAPI;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.tugasnegaraprojectjava.database.model.User;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RestApiReceive {
    User user = new User();

    public void receiveFromDatabase() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("messageFromDatabase", false, false, false, null);
        System.out.println(" [*] Waiting for messages from database");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume("messageFromDatabase", true, deliverCallback, consumerTag -> {
        });
    }

    public User receiveFromRegister() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("message-register", false, false, false, null);
        System.out.println(" [*] Waiting for messages from database");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

            if (message.equals("email sudah terdaftar")) {
                user = null;
            } else {
                user = new Gson().fromJson(message, User.class);
            }
        };
        channel.basicConsume("message-register", true, deliverCallback, consumerTag -> {
        });
        delay();
        return user;
    }

    public User receiveFromUser() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("message-login", false, false, false, null);
        System.out.println(" [*] Waiting for messages from database");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

            if (message.equals("error")) {
                user = null;
            } else {
                user = new Gson().fromJson(message, User.class);
            }
        };
        channel.basicConsume("message-login", true, deliverCallback, consumerTag -> {
        });
        delay();
        return user;
    }

    public void receiveResetPass() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("message-resetPassword", false, false, false, null);
        System.out.println(" [*] Waiting for messages from database");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume("message-resetPassword", true, deliverCallback, consumerTag -> { });
    }

    public void receiveforgotPass() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("message-forgotPassword", false, false, false, null);
        System.out.println(" [*] Waiting for messages from database");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume("message-forgotPassword", true, deliverCallback, consumerTag -> { });
    }


    private static void delay() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
