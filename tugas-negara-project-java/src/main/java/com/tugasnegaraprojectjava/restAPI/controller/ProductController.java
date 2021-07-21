package com.tugasnegaraprojectjava.restAPI.controller;

import com.google.gson.Gson;
import com.tugasnegaraprojectjava.database.model.Billing;
import com.tugasnegaraprojectjava.database.model.Pascabayar;
import com.tugasnegaraprojectjava.database.model.PaymentTB;
import com.tugasnegaraprojectjava.database.model.Prabayar;
import com.tugasnegaraprojectjava.restAPI.joinThirdParty.ThirdPartyService;
import com.tugasnegaraprojectjava.utility.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/merchant")
public class ProductController {

    @Autowired
    ThirdPartyService thirdPartyService;

    // ....................................... GET PRODUCT PASCABAYAR METHOD ......................................//

    // CHACK ALL PRODUCT PASCABAYAR //
    @RequestMapping(value = "/pascabayar/allproduct", method = RequestMethod.POST, produces = "application/json")
    public List<Pascabayar> pascabayarAllProduct() {
        return thirdPartyService.pascabayargetall();
    }


    // CHACK PRODUCT NAME PASCABAYAR //
    @RequestMapping(value = "/pascabayar/{product_name}", method = RequestMethod.POST, produces = "application/json")
    public List<Pascabayar> pascabayarProductName(@PathVariable final String product_name) {
        return thirdPartyService.pascabayargetProductName(product_name);
    }

    // ....................................... GET PRODUCT PRABAYAR METHOD ......................................//

    // CHACK ALL PRODUCT PRABAYAR //
    @RequestMapping(value = "/prabayar/allproduct", method = RequestMethod.POST, produces = "application/json")
    public List<Prabayar> prabayarAllProduct() {
        return thirdPartyService.prabayargetall();
    }


    // CHACK PRODUCT NAME PRABAYAR //
    @RequestMapping(value = "/prabayar/{product_name}", method = RequestMethod.POST, produces = "application/json")
    public List<Prabayar> prabayarProductName(@PathVariable final String product_name) {
        return thirdPartyService.prabayarGetProductname(product_name);
    }



// ........................................... PAYMENT METHOD ...............................................//

    // ORDER PASCABAYAR PRODUCT //
    @RequestMapping(value = "/billing/pascabayar", method = RequestMethod.POST)
    public ResponseEntity<?> paymentPascabayar(@RequestBody PaymentTB pay) {
         thirdPartyService.orderPascabayar(pay);
        if (pay.getStatus().equals("done")) {
            String message = "PAYMENT SUCCESS";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
        } else {
            String message = "PAYMENT FAILED";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
        }
    }

    // ORDER PRABAYAR PRODUCT //
    @RequestMapping(value = "/billing/prabayar", method = RequestMethod.POST)
    public ResponseEntity<?> paymentPrabayar(@RequestBody PaymentTB pay) {
        thirdPartyService.orderPrabayar(pay);
        if (pay.getStatus().equals("done")) {
            String message = "PAYMENT SUCCESS";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
        } else {
            String message = "PAYMENT FAILED";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
        }
    }

    // ........................................... BILLING METHOD ...............................................//

    // BILLING CUSTOMER CHECK //
    @RequestMapping(value = "/billing/customer/{customer_id}", method = RequestMethod.GET, produces = "application/json")
    public List<Billing> customerbilling(@PathVariable final int customer_id) {
        return thirdPartyService.customerBilling(customer_id);
    }

    // PAYMENT CUSTOMER BILLING //
    @RequestMapping(value = "/billing/order", method = RequestMethod.POST)
    public ResponseEntity<?> billingOrder(@RequestBody Billing bill) {
        thirdPartyService.orderBilling(bill);
        String message = "pembayaran billing berhasil";
        ResponseMessage responseMessage = new ResponseMessage(message);
        return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
    }
}
