package com.spring.hibernate.api_appchat.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "privatemessages")
public class PrivateMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sender_id")
//    @Column(name = "sender_id")
    private User sender;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recipient_id")
//    @Column(name = "recipient_id")
    private User recipient;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private String createdAt;
}
