package com.spring.hibernate.api_appchat.Service;

import com.spring.hibernate.api_appchat.Dao.ChatRoomDao;
import com.spring.hibernate.api_appchat.Dto.ChatRoomDto;
import com.spring.hibernate.api_appchat.Dto.JoinRoomDto;
import com.spring.hibernate.api_appchat.Entity.ChatRoom;
import org.springframework.http.ResponseEntity;

public interface ChatRoomService {
    ChatRoom findById(long id);
    ResponseEntity<?> findByRoomName(String name);
    ResponseEntity<?> createRoom(ChatRoomDto chatRoomDto);
    ResponseEntity<?> deleteRoom(long roomId, long hostId);
    ResponseEntity<?> chatRoomDetails(long roomId);
    ResponseEntity<?> editChatroom(ChatRoomDto chatRoomDto);
    ResponseEntity<?> addUser(JoinRoomDto joinRoomDto);
    ResponseEntity<?> removeUser(JoinRoomDto joinRoomDto);
    ResponseEntity<?> findUserByDisplayName(long roomId, String displayName);
}
