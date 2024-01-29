package com.spring.hibernate.api_appchat.ServiceImpl;

import com.spring.hibernate.api_appchat.Dao.AuthDao;
import com.spring.hibernate.api_appchat.Dao.UserDao;
import com.spring.hibernate.api_appchat.Dto.AuthDto;
import com.spring.hibernate.api_appchat.Entity.User;
import com.spring.hibernate.api_appchat.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthDao authDao;
    private final UserDao userDao;

    @Autowired
    public AuthServiceImpl(AuthDao authDao, UserDao userDao) {
        this.authDao = authDao;
        this.userDao = userDao;
    }

    private String getRole(User user) {
        String result = "";
        if (user.getIsAdmin() == 0) result = "ADMIN";
        else if (user.getIsAdmin() == 1) result = "CUSTOMER";
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authDao.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password !!!");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(getRole(user))));
    }

    @Override
    public ResponseEntity<?> signUp(String email) {
        return authDao.signUp(email);
    }

    @Override
    public ResponseEntity<?> confirmSignUp(AuthDto authDto) {
        return authDao.confirmSignUp(authDto);
    }

    @Override
    public ResponseEntity<?> signIn(String email, String password) {
        return authDao.signIn(email, password);
    }

    @Override
    public ResponseEntity<?> forgotPassword(String email) {
        return authDao.forgotPassword(email);
    }

    @Override
    public ResponseEntity<?> resetPassword(AuthDto authDto) {
        return authDao.resetPassword(authDto);
    }
}
