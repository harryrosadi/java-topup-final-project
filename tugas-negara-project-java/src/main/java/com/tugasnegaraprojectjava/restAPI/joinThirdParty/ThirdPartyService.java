package com.tugasnegaraprojectjava.restAPI.joinThirdParty;

import com.tugasnegaraprojectjava.database.model.Billing;
import com.tugasnegaraprojectjava.database.model.Pascabayar;
import com.tugasnegaraprojectjava.database.model.PaymentTB;
import com.tugasnegaraprojectjava.database.model.Prabayar;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Service
public class ThirdPartyService {

    private final RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(60000))
                .setReadTimeout(Duration.ofMillis(60000))
                .build();
    }

    public ThirdPartyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();

    }

    // ---------------------------------  PASCABAYAR    --------------------------------------- //
    // get all product pascabayar //
    public List<Pascabayar> pascabayargetall() {
        String url = "http://localhost:8081/merchant/pascabayar/readall/";
//        System.out.println(restTemplate.getForObject(url, ProductResponse[].class));
        return Arrays.asList(this.restTemplate.getForObject(url, Pascabayar[].class));
    }

    // get by product name PASCABAYAR //
    public List<Pascabayar> pascabayargetProductName(String product_name) {
        String url = "http://localhost:8081/merchant/pascabayar/product/" + product_name;
//        System.out.println(restTemplate.getForObject(url, Pascabayar[].class));
        return Arrays.asList(this.restTemplate.getForObject(url, Pascabayar[].class));
    }


    // ---------------------------------  PRABAYAR    --------------------------------------- //
    // get all PRODUCT PRABAYAR //
    public List<Prabayar> prabayargetall() {
        String url = "http://localhost:8081/merchant/prabayar/readall/";
//        System.out.println(restTemplate.getForObject(url, ProductResponse[].class));
        return Arrays.asList(this.restTemplate.getForObject(url, Prabayar[].class));
    }

    // get by product name PRABAYAR //
    public List<Prabayar> prabayarGetProductname(String product_name) {
        String url = "http://localhost:8081/merchant/prabayar/product/" + product_name;
//        System.out.println(restTemplate.getForObject(url, Pascabayar[].class));
        return Arrays.asList(this.restTemplate.getForObject(url, Prabayar[].class));
    }

    // ---------------------------------  PAYMENT  --------------------------------------- //

    // BILLING FOR PASCABAYAR PRODUCT //
    public PaymentTB orderPascabayar(PaymentTB pay) {
        String url = "http://localhost:8081/merchant/payment/pascabayar";
        return (this.restTemplate.postForObject(url, pay, PaymentTB.class));
    }

    // BILLING FOR PRABAYAR PRODUCT //
    public PaymentTB orderPrabayar(PaymentTB pay) {
        String url = "http://localhost:8081/merchant/payment/prabayar";
        return (this.restTemplate.postForObject(url, pay, PaymentTB.class));
    }


    // ---------------------------------  BILLING  --------------------------------------- //

    //
    public List<Billing> customerBilling(int customer_id) {
        String url = "http://localhost:8081/merchant/billing/product/" + customer_id;
        System.out.println(restTemplate.getForObject(url, Pascabayar[].class));
        return Arrays.asList(this.restTemplate.getForObject(url, Billing[].class));
    }

    public Billing orderBilling(Billing billing) {
        String url = "http://localhost:8081/merchant/billing/payment/";
//        System.out.println(restTemplate.getForObject(url, Pascabayar[].class));
        return (this.restTemplate.postForObject(url, billing, Billing.class));
    }


}