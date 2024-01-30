package com.spring.hibernate.api_appchat.Controller;

import com.spring.hibernate.api_appchat.Dto.ChatRoomDto;
import com.spring.hibernate.api_appchat.Service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chatroom/")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("find")
    public ResponseEntity<?> findByName(String name) {
        return ResponseEntity.ok(chatRoomService.findByRoomName(name));
    }

    @PostMapping("create")
    public ResponseEntity<?> createRoom(@RequestBody ChatRoomDto chatRoomDto) {
        return ResponseEntity.ok(chatRoomService.createRoom(chatRoomDto));
    }
}
