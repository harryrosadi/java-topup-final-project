package com.tugasnegaraprojectjava.restAPI.rabbitmqAPI;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class RestApiSend {

    public void sendToRabbit(String message, String param) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(param, false, false, false, null);

            channel.basicPublish("", param, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            System.out.println("Gagal mengirim pesan ke RestApi.." + e);
        }
    }
}
