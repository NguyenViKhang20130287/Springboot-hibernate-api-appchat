package com.spring.hibernate.api_appchat.ServiceImpl;

import com.spring.hibernate.api_appchat.Dao.UserDao;
import com.spring.hibernate.api_appchat.Entity.User;
import com.spring.hibernate.api_appchat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public User findByEmail(String email) {
        return null;
    }
}
