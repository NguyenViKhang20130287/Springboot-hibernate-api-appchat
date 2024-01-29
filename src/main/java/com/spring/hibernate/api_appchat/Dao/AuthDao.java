package com.spring.hibernate.api_appchat.Dao;

import com.spring.hibernate.api_appchat.Dto.AuthDto;
import com.spring.hibernate.api_appchat.Entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthDao {
    User findByEmail(String email);
    ResponseEntity<?> signUp(String email);
    ResponseEntity<?> confirmSignUp(AuthDto authDto);
    ResponseEntity<?> signIn(String email, String password);
    ResponseEntity<?> forgotPassword(String email);
    ResponseEntity<?> resetPassword(AuthDto authDto);
}
