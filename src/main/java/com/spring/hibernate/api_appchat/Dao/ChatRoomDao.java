package com.spring.hibernate.api_appchat.Dao;

import com.spring.hibernate.api_appchat.Dto.ChatRoomDto;
import com.spring.hibernate.api_appchat.Entity.ChatRoom;
import org.springframework.http.ResponseEntity;

public interface ChatRoomDao {
    ChatRoom findById(long id);
    ResponseEntity<?> findByRoomName(String name);
    ResponseEntity<?> createRoom(ChatRoomDto chatRoomDto);
}
