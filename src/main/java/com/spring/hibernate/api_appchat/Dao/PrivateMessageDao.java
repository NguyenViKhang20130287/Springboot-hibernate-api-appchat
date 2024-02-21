package com.spring.hibernate.api_appchat.Dao;

import com.spring.hibernate.api_appchat.Dto.PrivateMessageDto;
import org.springframework.http.ResponseEntity;

public interface PrivateMessageDao {
    ResponseEntity<?> sendMessage(PrivateMessageDto privateMessageDto);
    ResponseEntity<?> loadAllMessage(PrivateMessageDto privateMessageDto);
}
