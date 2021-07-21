package com.example.finalprojectproduct.controller;


import com.example.finalprojectproduct.model.Pascabayar;
import com.example.finalprojectproduct.model.Payment;
import com.example.finalprojectproduct.model.Prabayar;
import com.example.finalprojectproduct.service.ServicePascabayar;
import com.example.finalprojectproduct.service.ServicePayment;
import com.example.finalprojectproduct.service.ServicePrabayar;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/merchant")
public class ControllerPascabayarPrabayar {

    @Autowired
    ServicePascabayar servicePascabayar;
    @Autowired
    ServicePrabayar servicePrabayar;
    @Autowired
    ServicePayment servicePayment;


    // ---------------------------------  PASCABAYAR    --------------------------------------- //


    // PASCABAYAR READ ALL PRODUCT //
    @RequestMapping(value = "/pascabayar/readall", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> listAllProducts() throws Exception {

        List<Pascabayar> product = servicePascabayar.readall();
        System.out.println(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/pascabayar/product/{product_name}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> readbyname(@PathVariable String product_name) throws Exception {

        List<Pascabayar> product = servicePascabayar.readByName(product_name);
        System.out.println(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // POSTBILL INSERT PRODUCT //
    @RequestMapping(value = "/pascabayar/insert", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> insertproduct(@RequestBody Pascabayar pascabayar) throws SQLException, IOException, ClassNotFoundException, TimeoutException {

        servicePascabayar.insertProduct(new Gson().toJson(pascabayar));

        return new ResponseEntity<>(pascabayar, HttpStatus.CREATED);
    }


    // ---------------------------------  PRABYAR    --------------------------------------- //
    // PREPAID PRODUCT BY NAME//
    @RequestMapping(value = "/prabayar/product/{product_name}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> productbyname(@PathVariable String product_name) throws Exception {

        List<Prabayar> product = servicePrabayar.readByName(product_name);
        System.out.println(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // PREPAID READ ALL PRODUCT //
    @RequestMapping(value = "/prabayar/readall", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> productPrepaid() throws Exception {

        List<Prabayar> product = servicePrabayar.readall();
        System.out.println(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    // PREPAID INSERT PRODUCT //
    @RequestMapping(value = "/prabayar/insert", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> insertprepaid(@RequestBody Prabayar prabayar) throws SQLException, IOException, ClassNotFoundException, TimeoutException {

        servicePrabayar.insertProduct(new Gson().toJson(prabayar));

        return new ResponseEntity<>(prabayar, HttpStatus.CREATED);
    }




}
