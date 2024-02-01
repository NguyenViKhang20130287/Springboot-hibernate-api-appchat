package com.spring.hibernate.api_appchat.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "chatrooms")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String roomName;

    @Column(name = "password_room")
    private String passwordRoom;

    @Column(name = "avatar_room")
    private String avatarRoom;

    @Column(name = "created_at")
    private String createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @OneToMany(mappedBy = "chatRoom")
    private List<RoomMember> members;

}
