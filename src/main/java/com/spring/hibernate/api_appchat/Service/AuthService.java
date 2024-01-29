package com.spring.hibernate.api_appchat.Service;

import com.spring.hibernate.api_appchat.Dao.AuthDao;
import com.spring.hibernate.api_appchat.Dto.AuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    ResponseEntity<?> signUp(String email);
    ResponseEntity<?> confirmSignUp(AuthDto authDto);
    ResponseEntity<?> signIn(String email, String password);
    ResponseEntity<?> forgotPassword(String email);
    ResponseEntity<?> resetPassword(AuthDto authDto);
}
