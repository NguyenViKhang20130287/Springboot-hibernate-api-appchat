package com.spring.hibernate.api_appchat.Controller;

import com.spring.hibernate.api_appchat.Dto.AuthDto;
import com.spring.hibernate.api_appchat.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestParam String email) {
        return ResponseEntity.ok(authService.signUp(email));
    }

    @PostMapping("sign-up/confirm")
    public ResponseEntity<?> confirmSignUp(@RequestBody AuthDto authDto) {
        return ResponseEntity.ok(authService.confirmSignUp(authDto));
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(authService.signIn(email, password));
    }

    @PostMapping("forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        return ResponseEntity.ok(authService.forgotPassword(email));
    }

    @PostMapping("forgot-password/confirm")
    public ResponseEntity<?> confirmForgotPassword(@RequestBody AuthDto authDto) {
        return ResponseEntity.ok(authService.resetPassword(authDto));
    }
}
