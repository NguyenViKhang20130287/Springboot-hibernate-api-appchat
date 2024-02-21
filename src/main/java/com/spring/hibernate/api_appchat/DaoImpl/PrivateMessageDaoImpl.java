package com.spring.hibernate.api_appchat.DaoImpl;

import com.spring.hibernate.api_appchat.Dao.PrivateMessageDao;
import com.spring.hibernate.api_appchat.Dto.PrivateMessageDto;
import com.spring.hibernate.api_appchat.Entity.PrivateMessage;
import com.spring.hibernate.api_appchat.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PrivateMessageDaoImpl implements PrivateMessageDao {

    private EntityManager entityManager;

    @Autowired
    public PrivateMessageDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public ResponseEntity<?> sendMessage(PrivateMessageDto privateMessageDto) {
        User sender = entityManager.find(User.class, privateMessageDto.getSenderId());
        User recipient = entityManager.find(User.class, privateMessageDto.getRecipientId());
        if (sender == null || recipient == null) {
            return new ResponseEntity<>("User not found!!!", HttpStatus.NOT_FOUND);
        }
        PrivateMessage privateMessage = new PrivateMessage();
        privateMessage.setSender(sender);
        privateMessage.setRecipient(recipient);
        privateMessage.setContent(privateMessageDto.getContent());
        privateMessage.setCreatedAt(String.valueOf(LocalDateTime.now()));
        entityManager.persist(privateMessage);
        return new ResponseEntity<>("Sent message successful", HttpStatus.OK);
    }
}
