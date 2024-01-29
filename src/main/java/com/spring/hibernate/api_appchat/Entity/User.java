package com.spring.hibernate.api_appchat.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "is_admin")
    private int isAdmin;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private List<ChatRoom> hostedChatRooms;

//    @OneToMany(mappedBy = "member")
//    private List<RoomMember> joinedRooms;
}