package com.spring.hibernate.api_appchat.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class ChatRoomMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private String createdAt;
}
