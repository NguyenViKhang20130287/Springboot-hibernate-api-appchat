package com.spring.hibernate.api_appchat.Dao;

import com.spring.hibernate.api_appchat.Dto.JoinRoomDto;
import com.spring.hibernate.api_appchat.Dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserDao {
    ResponseEntity<?> findByEmail(String email);
    ResponseEntity<?> findById(long id);
    ResponseEntity<?> editUser(UserDto userDto);
    ResponseEntity<?> joinRoom(JoinRoomDto joinRoomDto);
    ResponseEntity<?> exitRoom(long roomId, long userId);
}
