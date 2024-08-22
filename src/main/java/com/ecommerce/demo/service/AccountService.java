package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Account;
import com.ecommerce.demo.model.UserWrapper;
import com.ecommerce.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseEntity<String> register(UserWrapper uw) {
        System.out.println("....User Created....");

        try {
            Account newUser = new Account(uw);
            newUser.setPassword(encoder.encode(newUser.getPassword()));
            repo.save(newUser);
            //System.out.println("....User Created....");
            return new ResponseEntity<>("Registered", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> verify(UserWrapper user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(jwtService.generateToken(user.getUsername()), HttpStatus.OK);
        }

        return new ResponseEntity<>("Failed", HttpStatus.UNAUTHORIZED);
    }
}
