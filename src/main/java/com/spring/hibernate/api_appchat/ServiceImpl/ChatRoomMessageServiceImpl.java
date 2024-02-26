package com.spring.hibernate.api_appchat.ServiceImpl;

import com.spring.hibernate.api_appchat.Dao.ChatRoomMessageDao;
import com.spring.hibernate.api_appchat.Dto.ChatRoomMessageDto;
import com.spring.hibernate.api_appchat.Service.ChatRoomMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomMessageServiceImpl implements ChatRoomMessageService {
    private ChatRoomMessageDao chatRoomMessageDao;
    @Autowired
    public ChatRoomMessageServiceImpl(ChatRoomMessageDao chatRoomMessageDao){
        this.chatRoomMessageDao = chatRoomMessageDao;
    }

    @Override
    public ResponseEntity<?> sendMessage(ChatRoomMessageDto chatRoomMessageDto) {
        return chatRoomMessageDao.sendMessage(chatRoomMessageDto);
    }

    @Override
    public ResponseEntity<?> loadAllMessage(ChatRoomMessageDto chatRoomMessageDto) {
        return chatRoomMessageDao.loadAllMessage(chatRoomMessageDto);
    }
}
