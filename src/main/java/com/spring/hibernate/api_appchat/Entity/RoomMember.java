package com.spring.hibernate.api_appchat.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name = "roommembers")
public class RoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

//    @JsonIgnore
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User joinedUser;

    @Column(name = "joined_at")
    private String joinedAt;

}
