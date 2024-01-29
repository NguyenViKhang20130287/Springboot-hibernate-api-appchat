package com.spring.hibernate.api_appchat.ServiceImpl;

import com.spring.hibernate.api_appchat.Dao.UserDao;
import com.spring.hibernate.api_appchat.Dto.UserDto;
import com.spring.hibernate.api_appchat.Entity.User;
import com.spring.hibernate.api_appchat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public ResponseEntity<?> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public ResponseEntity<?> findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public ResponseEntity<?> editUser(UserDto userDto) {
        return userDao.editUser(userDto);
    }
}
