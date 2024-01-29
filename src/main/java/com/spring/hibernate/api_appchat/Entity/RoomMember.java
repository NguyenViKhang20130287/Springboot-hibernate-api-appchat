package com.spring.hibernate.api_appchat.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roommembers")
public class RoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User member;

    @Column(name = "joined_at")
    private String joinedAt;

//    @Transient
//    private UserInfoDto userInfo;
}
