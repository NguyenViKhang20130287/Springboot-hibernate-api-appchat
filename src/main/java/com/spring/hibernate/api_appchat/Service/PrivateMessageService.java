package com.spring.hibernate.api_appchat.Service;

import com.spring.hibernate.api_appchat.Dto.PrivateMessageDto;
import org.springframework.http.ResponseEntity;

public interface PrivateMessageService {
    ResponseEntity<?> sendMessage(PrivateMessageDto privateMessageDto);
}
