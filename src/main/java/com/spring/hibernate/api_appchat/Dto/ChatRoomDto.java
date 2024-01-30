package com.spring.hibernate.api_appchat.Dto;

import lombok.Data;

@Data
public class ChatRoomDto {
    private long id;
    private String name;
    private String passwordRoom;
    private long hostId;
    private String avatarRoom;
}
