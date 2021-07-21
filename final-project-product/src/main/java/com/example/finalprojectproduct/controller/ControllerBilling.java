package com.example.finalprojectproduct.controller;


import com.example.finalprojectproduct.Message.ResponseMessage;
import com.example.finalprojectproduct.model.Billing;
import com.example.finalprojectproduct.model.Payment;
import com.example.finalprojectproduct.service.ServiceBilling;
import com.example.finalprojectproduct.service.ServicePayment;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/merchant")
public class ControllerBilling {

    @Autowired
    ServiceBilling serviceBilling;

    // ---------------------------------  BILLING    --------------------------------------- //

    // BILLING CUSTOMER//
    @RequestMapping(value = "/billing/product/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> billingcustomer(@PathVariable int id) throws Exception {

        List<Billing> product = serviceBilling.billingCustomer(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/billing/payment", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> billingpayment (@RequestBody Billing bills) throws IOException, TimeoutException {

        serviceBilling.updateBilling(new Gson().toJson(bills));
        String message = "lunas";
        ResponseMessage responseMessage = new ResponseMessage(message);
        return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
    }



        // ---------------------------------    UTILITY    --------------------------------------- //

    // INSERT BILLING DATA //
    @RequestMapping(value = "/billing/insert", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> insertbilling(@RequestBody Billing bills) throws IOException {

        serviceBilling.insertBilling(new Gson().toJson(bills));
        String message = "insset berhasil";
        ResponseMessage responseMessage = new ResponseMessage(message);
        return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
    }

    // BILLING HISTORY //
    @RequestMapping(value = "/billing/history", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> ALLPAYMENT() throws Exception {

        List<Billing> product = serviceBilling.billingHistory();
        System.out.println(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // BILLING CHECK BY STATUS//
    @RequestMapping(value = "/billing/product/status/{status}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> PAYMENTBYPRODUCT(@PathVariable String status) throws Exception {

        List<Billing> product = serviceBilling.billingStatus(status);
        System.out.println(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
