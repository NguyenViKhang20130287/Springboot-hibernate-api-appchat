package com.spring.hibernate.api_appchat.ServiceImpl;

import com.spring.hibernate.api_appchat.Dao.ChatRoomDao;
import com.spring.hibernate.api_appchat.Dto.ChatRoomDto;
import com.spring.hibernate.api_appchat.Dto.JoinRoomDto;
import com.spring.hibernate.api_appchat.Entity.ChatRoom;
import com.spring.hibernate.api_appchat.Service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomDao chatRoomDao;

    @Autowired
    public ChatRoomServiceImpl(ChatRoomDao chatRoomDao) {
        this.chatRoomDao = chatRoomDao;
    }

    @Override
    public ChatRoom findById(long id) {
        return chatRoomDao.findById(id);
    }

    @Override
    public ResponseEntity<?> findByRoomName(String name) {
        return chatRoomDao.findByRoomName(name);
    }

    @Override
    public ResponseEntity<?> createRoom(ChatRoomDto chatRoomDto) {
        return chatRoomDao.createRoom(chatRoomDto);
    }

    @Override
    public ResponseEntity<?> deleteRoom(long roomId, long hostId) {
        return chatRoomDao.deleteRoom(roomId, hostId);
    }

    @Override
    public ResponseEntity<?> chatRoomDetails(long roomId) {
        return chatRoomDao.chatRoomDetails(roomId);
    }

    @Override
    public ResponseEntity<?> editChatroom(ChatRoomDto chatRoomDto) {
        return chatRoomDao.editChatroom(chatRoomDto);
    }

    @Override
    public ResponseEntity<?> addUser(JoinRoomDto joinRoomDto) {
        return chatRoomDao.addUser(joinRoomDto);
    }

    @Override
    public ResponseEntity<?> removeUser(JoinRoomDto joinRoomDto) {
        return chatRoomDao.removeUser(joinRoomDto);
    }

    @Override
    public ResponseEntity<?> findUserByDisplayName(long roomId, String displayName) {
        return chatRoomDao.findUserByDisplayName(roomId, displayName);
    }
}
