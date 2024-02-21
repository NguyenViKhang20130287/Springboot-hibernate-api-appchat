package com.spring.hibernate.api_appchat.Service;

import com.spring.hibernate.api_appchat.Dto.ChatRoomMessageDto;
import org.springframework.http.ResponseEntity;

public interface ChatRoomMessageService {
    ResponseEntity<?> sendMessage(ChatRoomMessageDto chatRoomMessageDto);
    ResponseEntity<?> loadAllMessage(ChatRoomMessageDto chatRoomMessageDto);
}
