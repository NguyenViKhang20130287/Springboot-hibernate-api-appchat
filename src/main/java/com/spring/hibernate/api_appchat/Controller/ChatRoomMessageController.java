package com.spring.hibernate.api_appchat.Controller;

import com.spring.hibernate.api_appchat.Dto.ChatRoomMessageDto;
import com.spring.hibernate.api_appchat.Service.ChatRoomMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chatroom-message/")
public class ChatRoomMessageController {
    private ChatRoomMessageService chatRoomMessageService;
    @Autowired
    public ChatRoomMessageController(ChatRoomMessageService chatRoomMessageService){
        this.chatRoomMessageService = chatRoomMessageService;
    }

    @PostMapping("send")
    public ResponseEntity<?> sendMessage(@RequestBody ChatRoomMessageDto chatRoomMessageDto){
        return ResponseEntity.ok(chatRoomMessageService.sendMessage(chatRoomMessageDto));
    }

    @GetMapping("load-all")
    public ResponseEntity<?> loadAllMessage(@RequestBody ChatRoomMessageDto chatRoomMessageDto){
        return ResponseEntity.ok(chatRoomMessageService.loadAllMessage(chatRoomMessageDto));
    }
}
