package com.example.finalprojectproduct.controller;


import com.example.finalprojectproduct.model.CustomerData;
import com.example.finalprojectproduct.model.Pascabayar;
import com.example.finalprojectproduct.service.ServiceCustomer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/customer")
public class ControllerCustomer {

    @Autowired
    ServiceCustomer serviceCustomer;
    // ---------------------------------  CUSTOMER    --------------------------------------- //
    // POSTBILL INSERT PRODUCT //
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> insertproduct(@RequestBody Pascabayar pascabayar) throws SQLException, IOException, ClassNotFoundException, TimeoutException {

        serviceCustomer.insertNewCustomer(new Gson().toJson(pascabayar));

        return new ResponseEntity<>(pascabayar, HttpStatus.CREATED);
    }


    // PASCABAYAR READ ALL PRODUCT //
    @RequestMapping(value = "/readall", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> listAllProducts() throws Exception {

        List<CustomerData> data = serviceCustomer.allData();
        System.out.println(data);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
