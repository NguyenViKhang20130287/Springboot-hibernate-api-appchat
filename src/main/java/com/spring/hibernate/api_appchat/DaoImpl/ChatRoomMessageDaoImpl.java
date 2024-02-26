package com.spring.hibernate.api_appchat.DaoImpl;

import com.spring.hibernate.api_appchat.Dao.ChatRoomMessageDao;
import com.spring.hibernate.api_appchat.Dto.ChatRoomMessageDto;
import com.spring.hibernate.api_appchat.Entity.ChatRoom;
import com.spring.hibernate.api_appchat.Entity.ChatRoomMessage;
import com.spring.hibernate.api_appchat.Entity.RoomMember;
import com.spring.hibernate.api_appchat.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ChatRoomMessageDaoImpl implements ChatRoomMessageDao {
    private EntityManager entityManager;

    @Autowired
    public ChatRoomMessageDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public ResponseEntity<?> sendMessage(ChatRoomMessageDto chatRoomMessageDto) {
        ChatRoom chatRoom = entityManager.find(ChatRoom.class, chatRoomMessageDto.getRoomId());
        User sender = entityManager.find(User.class, chatRoomMessageDto.getSenderId());
        if (chatRoom == null) return new ResponseEntity<>("Room not found!!!", HttpStatus.NOT_FOUND);
        if (sender == null) return new ResponseEntity<>("User not found!!!", HttpStatus.NOT_FOUND);
        List<RoomMember> members = chatRoom.getMembers();
        for (RoomMember member : members){
            if (member.getJoinedUser().getId() == sender.getId()){
                ChatRoomMessage chatRoomMessage = new ChatRoomMessage();
                chatRoomMessage.setChatRoom(chatRoom);
                chatRoomMessage.setSender(sender);
                chatRoomMessage.setContent(chatRoomMessageDto.getContent());
                chatRoomMessage.setCreatedAt(String.valueOf(LocalDateTime.now()));
                entityManager.persist(chatRoomMessage);
                return new ResponseEntity<>("Sent message to room successful", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User has not joined the chat room", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> loadAllMessage(ChatRoomMessageDto chatRoomMessageDto) {
        ChatRoom chatRoom = entityManager.find(ChatRoom.class, chatRoomMessageDto.getRoomId());
        User sender = entityManager.find(User.class, chatRoomMessageDto.getSenderId());
        if (chatRoom == null) return new ResponseEntity<>("Room not found!!!", HttpStatus.NOT_FOUND);
        if (sender == null) return new ResponseEntity<>("User not found!!!", HttpStatus.NOT_FOUND);
        List<RoomMember> members = chatRoom.getMembers();
        String query = "from ChatRoomMessage where chatRoom=?1  order by createdAt asc";
        for (RoomMember member : members){
            if (member.getJoinedUser().getId() == sender.getId()){
                TypedQuery<ChatRoomMessage> roomMessages = entityManager.createQuery(query, ChatRoomMessage.class);
                roomMessages.setParameter(1, chatRoom);
                List<ChatRoomMessage> result = roomMessages.getResultList().isEmpty() ? null : roomMessages.getResultList();
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User has not joined the chat room", HttpStatus.BAD_REQUEST);
    }
}
