package com.example.finalprojectproduct.controller;

import com.example.finalprojectproduct.Message.ResponseMessage;
import com.example.finalprojectproduct.model.Payment;
import com.example.finalprojectproduct.service.ServicePayment;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/merchant")
public class ControllerPayment {

    @Autowired
    ServicePayment servicePayment;

    // ---------------------------------  PAYMENT    --------------------------------------- //
    // PAYMENT INSERT PASCABAYAR //
    @RequestMapping(value = "/payment/pascabayar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> INSERTPAYMENT(@RequestBody Payment payment) throws IOException {

        servicePayment.insertPayment(new Gson().toJson(payment));
        delay();

        String status = servicePayment.pascabayarOrderId(payment.getOrder_id());
        if (status.equals("done")) {
            System.out.println("PAYMENT SUCCESS");
            String message = "payment successful";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
        } else {
            servicePayment.deleteOrder(payment.getOrder_id());
            String message = "payment failed";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
        }
    }

    // PAYMENT INSERT PRABAYAR //
    @RequestMapping(value = "/payment/prabayar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> prabayar(@RequestBody Payment payment) throws IOException {

        servicePayment.insertPayment(new Gson().toJson(payment));
        delay();

        String status = servicePayment.prabayarOrderId(payment.getOrder_id());
        if (status.equals("done")) {
            System.out.println("PAYMENT SUCCESS");
            String message = "payment successful";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
        } else {
            servicePayment.deleteOrder(payment.getOrder_id());
            String message = "payment failed";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
        }
    }


    // ---------------------------------  UTILITY  --------------------------------------- //

    // PAYMENT READ ALL PRODUCT //
    @RequestMapping(value = "/payment/readall", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> ALLPAYMENT() throws Exception {

        List<Payment> product = servicePayment.readallPayment();
        System.out.println(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // PAYMENT PRODUCT BY NAME//
    @RequestMapping(value = "/payment/product/{product_name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> PAYMENTBYPRODUCT(@PathVariable String product_name) throws Exception {

        List<Payment> product = servicePayment.paymentProductName(product_name);
        System.out.println(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    private static void delay() {
        try {
            Thread.sleep(25000);
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
