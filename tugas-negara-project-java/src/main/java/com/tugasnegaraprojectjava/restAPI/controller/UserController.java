package com.tugasnegaraprojectjava.restAPI.controller;

import com.google.gson.Gson;
import com.tugasnegaraprojectjava.database.model.User;
import com.tugasnegaraprojectjava.database.service.UserForgotPassword;
import com.tugasnegaraprojectjava.restAPI.rabbitmqAPI.RestApiReceive;
import com.tugasnegaraprojectjava.restAPI.rabbitmqAPI.RestApiSend;
import com.tugasnegaraprojectjava.utility.ResponseMessage;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    RestApiSend restApiSend = new RestApiSend();
    RestApiReceive restApiReceive = new RestApiReceive();
    UserForgotPassword userForgotPassword = new UserForgotPassword();

    // register user //
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> register(@RequestBody User users) throws IOException, TimeoutException {

        restApiSend.sendToRabbit(new Gson().toJson(users), "register");
        User user1 = restApiReceive.receiveFromRegister();
        if (user1 == null) {
            String message = "email sudah terdaftar";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
        } else {
            String message = "sukses register";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
        }
    }

    // login user //
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> login(@RequestBody User users) throws IOException, TimeoutException {
        try {
            restApiSend.sendToRabbit(new Gson().toJson(users), "login");
        } catch (Exception e) {
            System.out.println(e);
        }

        User user1 = restApiReceive.receiveFromUser();
        if (user1 != null) {
            String message = "suskses login";
            ResponseMessage responseMessage = new ResponseMessage(message);
            String token = getJWTToken(users.getEmail());
            User user = new User();
            user.setEmail(users.getEmail());
            user.setToken(token);
            return new ResponseEntity<>(new Gson().toJson(user), HttpStatus.CREATED);
        } else {
            String message = "gagal login";
            ResponseMessage responseMessage = new ResponseMessage(message);
            return new ResponseEntity<>(new Gson().toJson(responseMessage), HttpStatus.CREATED);
        }
    }

    // ....................................   FORGOT PASSWORD ..........................................//


    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) throws IOException {

        try {
            restApiSend.sendToRabbit(email, "forgot-passoword");
        } catch (Exception e) {
            System.out.println(e);
        }

        String response = userForgotPassword.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8080/user/reset-password?token=" + response;
        }
        return response;
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password) throws IOException {

        return userForgotPassword.resetPassword(token, password);
    }

    // ............................................  TOKEN ......................................... //

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }
}

