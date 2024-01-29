package com.spring.hibernate.api_appchat.ServiceImpl;

import com.spring.hibernate.api_appchat.Dao.AuthDao;
import com.spring.hibernate.api_appchat.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthDao authDao;

    @Autowired
    public AuthServiceImpl(AuthDao authDao){
        this.authDao = authDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
