package com.spring.hibernate.api_appchat.Dto;

import lombok.Data;

@Data
public class JoinRoomDto {
    private long roomId;
    private long userId;
    private String passwordRoom;
}
