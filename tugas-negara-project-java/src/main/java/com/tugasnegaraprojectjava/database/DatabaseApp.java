package com.tugasnegaraprojectjava.database;

import com.tugasnegaraprojectjava.database.rabbitmqDB.Receive;

public class DatabaseApp {
    public static Receive receive = new Receive();

    public static void main(String[] args) {
        try {
            System.out.println(" [*] Waiting for messages..");
            receive.register(); // REGISTER USER TO DATABASE TABLE USER //
            receive.login(); // LOGIN USER FROM DATABASE TABLE USER //
            receive.forgotPassword();
            receive.resetPassword();
        } catch (Exception e) {
            System.out.println("Error DatabaseMain = " + e);
        }
    }
}
