package com.spring.hibernate.api_appchat.Controller;

import com.spring.hibernate.api_appchat.Dto.UserDto;
import com.spring.hibernate.api_appchat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("find")
    public ResponseEntity<?> findUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("edit")
    public ResponseEntity<?> editUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.editUser(userDto));
    }
}
