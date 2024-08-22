package com.ecommerce.demo.controller;

import com.ecommerce.demo.model.UserWrapper;
import com.ecommerce.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AccountController {

    @Autowired
    AccountService uService;

    @PostMapping("/register")
    public ResponseEntity<String> registeration(@RequestBody UserWrapper uw) {
        return uService.register(uw);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserWrapper user) {
        return uService.verify(user);
    }
}
