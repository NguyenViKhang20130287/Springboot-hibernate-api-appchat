package com.spring.hibernate.api_appchat.Service;

import com.spring.hibernate.api_appchat.Entity.User;

public interface UserService {
    User findByEmail(String email);
}
