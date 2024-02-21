package com.spring.hibernate.api_appchat.Dao;

import com.spring.hibernate.api_appchat.Dto.ChatRoomMessageDto;
import org.springframework.http.ResponseEntity;

public interface ChatRoomMessageDao {
    ResponseEntity<?> sendMessage(ChatRoomMessageDto chatRoomMessageDto);
    ResponseEntity<?> loadAllMessage(ChatRoomMessageDto chatRoomMessageDto);
}
