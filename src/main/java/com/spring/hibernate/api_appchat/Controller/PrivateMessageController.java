package com.spring.hibernate.api_appchat.Controller;

import com.spring.hibernate.api_appchat.Dto.PrivateMessageDto;
import com.spring.hibernate.api_appchat.Entity.PrivateMessage;
import com.spring.hibernate.api_appchat.Service.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/private-message/")
public class PrivateMessageController {
    private PrivateMessageService privateMessageService;

    @Autowired
    public PrivateMessageController(PrivateMessageService privateMessageService) {
        this.privateMessageService = privateMessageService;
    }

    @PostMapping("send")
    public ResponseEntity<?> sendMessage(@RequestBody PrivateMessageDto privateMessageDto) {
        return ResponseEntity.ok(privateMessageService.sendMessage(privateMessageDto));
    }

    @GetMapping("load-all")
    public ResponseEntity<?> loadAll(@RequestBody PrivateMessageDto privateMessageDto) {
        return ResponseEntity.ok(privateMessageService.loadAllMessage(privateMessageDto));
    }

}
