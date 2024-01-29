package com.spring.hibernate.api_appchat.Dao;

import com.spring.hibernate.api_appchat.Entity.User;

public interface UserDao {
    User findByEmail(String email);
}
