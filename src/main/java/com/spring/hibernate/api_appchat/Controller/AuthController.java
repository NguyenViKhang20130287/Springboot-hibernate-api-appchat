package com.spring.hibernate.api_appchat.Controller;

import com.spring.hibernate.api_appchat.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }


}
