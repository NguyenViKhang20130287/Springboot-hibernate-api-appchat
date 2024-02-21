package com.spring.hibernate.api_appchat.Dto;

import lombok.Data;

@Data
public class PrivateMessageDto {
    private long id;
    private long senderId;
    private long recipientId;
    private String content;
}
