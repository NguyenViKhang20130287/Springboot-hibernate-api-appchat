package com.spring.hibernate.api_appchat.ServiceImpl;

import com.spring.hibernate.api_appchat.Dao.ChatRoomDao;
import com.spring.hibernate.api_appchat.Dto.ChatRoomDto;
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
}
