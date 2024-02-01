package com.spring.hibernate.api_appchat.DaoImpl;

import com.spring.hibernate.api_appchat.Dao.ChatRoomDao;
import com.spring.hibernate.api_appchat.Dao.UserDao;
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
    private final UserDao userDao;

    @Autowired
    public ChatRoomDaoImpl(EntityManager entityManager, UserDao userDao) {
        this.entityManager = entityManager;
        this.userDao = userDao;
    }

    @Override
    public ChatRoom findById(long id) {
        return entityManager.find(ChatRoom.class, id);
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

    @Transactional
    @Override
    public ResponseEntity<?> deleteRoom(long roomId, long hostId) {
        ChatRoom chatRoom = findById(roomId);
        List<RoomMember> roomMembers = chatRoom.getMembers();
        if (chatRoom.getHost().getId() != hostId)
            return new ResponseEntity<>("No permission!!", HttpStatus.BAD_REQUEST);
        for (RoomMember roomMember : roomMembers){
            entityManager.remove(roomMember);
        }
        entityManager.remove(chatRoom);
        return new ResponseEntity<>("Delete successful", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> chatRoomDetails(long roomId) {
        ChatRoom chatRoom = entityManager.find(ChatRoom.class, roomId);
        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> editChatroom(ChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = findById(chatRoomDto.getId());
        chatRoom.setRoomName(chatRoomDto.getName());
        chatRoom.setPasswordRoom(chatRoomDto.getPasswordRoom());
        chatRoom.setAvatarRoom(chatRoomDto.getAvatarRoom());
        chatRoom.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }
}
