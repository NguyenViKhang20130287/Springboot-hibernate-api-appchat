package com.spring.hibernate.api_appchat.DaoImpl;

import com.spring.hibernate.api_appchat.Dao.ChatRoomDao;
import com.spring.hibernate.api_appchat.Dto.ChatRoomDto;
import com.spring.hibernate.api_appchat.Entity.ChatRoom;
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
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChatRoomDaoImpl implements ChatRoomDao {
    private final EntityManager entityManager;

    @Autowired
    public ChatRoomDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ChatRoom findById(long id) {
        ChatRoom chatRoom = entityManager.find(ChatRoom.class, id);
        return chatRoom;
    }

    @Override
    public ResponseEntity<?> findByRoomName(String name) {
        String query = "FROM ChatRoom where roomName=:name";
        TypedQuery<ChatRoom> typedQuery = entityManager.createQuery(query, ChatRoom.class);
        typedQuery.setParameter("name", name);
        if (typedQuery.getResultList().isEmpty()) return new ResponseEntity<>("Room not found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(typedQuery.getSingleResult(), HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> createRoom(ChatRoomDto chatRoomDto) {
        User user = entityManager.find(User.class, chatRoomDto.getHostId());
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomName(chatRoomDto.getName());
        chatRoom.setPasswordRoom(chatRoomDto.getPasswordRoom());
        chatRoom.setHost(user);
        chatRoom.setAvatarRoom(chatRoomDto.getAvatarRoom());
        List<RoomMember> roomMembers = new ArrayList<>();
        RoomMember roomMember = new RoomMember();
        roomMember.setChatRoom(chatRoom);
        roomMember.setJoinedUser(user);
        roomMember.setJoinedAt(String.valueOf(LocalDateTime.now()));
        roomMembers.add(roomMember);
        chatRoom.setMembers(roomMembers);
        chatRoom.setCreatedAt(String.valueOf(LocalDateTime.now()));
        entityManager.persist(chatRoom);
        entityManager.persist(roomMember);
        return new ResponseEntity<>(chatRoom, HttpStatus.CREATED);
    }
}
