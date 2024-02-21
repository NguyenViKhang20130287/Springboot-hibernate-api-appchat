package com.spring.hibernate.api_appchat.Dto;

import com.spring.hibernate.api_appchat.Entity.ChatRoom;
import com.spring.hibernate.api_appchat.Entity.User;
import lombok.Data;

@Data
public class MemberDto {
    private long id;
    private String joinedAt;
    private ChatRoom chatRoom;
    private User joinedUser;
}
