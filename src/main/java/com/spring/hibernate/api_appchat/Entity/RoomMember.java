package com.spring.hibernate.api_appchat.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roommembers")
public class RoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private ChatRoom chatRoom;

//    @Column(name = "room_id")
//    private long roomId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User joinedUser;

//    @Column(name = "user_id")
//    private long userId;

    @Column(name = "joined_at")
    private String joinedAt;

}
