package com.spring.hibernate.api_appchat.Service;

import com.spring.hibernate.api_appchat.Dto.UserDto;
import com.spring.hibernate.api_appchat.Entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?>  findByEmail(String email);
    ResponseEntity<?> findById(long id);
    ResponseEntity<?> editUser(UserDto userDto);
}
