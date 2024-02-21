package com.spring.hibernate.api_appchat.Dto;

import lombok.Data;

@Data
public class ChatRoomMessageDto {
    private long id;
    private long roomId;
    private long senderId;
    private String content;
}
