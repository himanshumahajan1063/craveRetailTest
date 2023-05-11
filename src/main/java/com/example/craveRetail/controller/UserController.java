package com.example.craveRetail.controller;

import com.example.craveRetail.controller.dto.UsersRequestDto;
import com.example.craveRetail.controller.dto.UsersResponseDto;
import com.example.craveRetail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("crave-retail/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UsersResponseDto>> getAllUsers() {
        List<UsersResponseDto> usersResponseDtoList = userService.getAllUsers();
        return ResponseEntity.ok(usersResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDto> getUserById(@PathVariable("id") final Long id) {
        UsersResponseDto usersResponseDto = userService.getUserById(id);
        if (usersResponseDto != null) {
            return ResponseEntity.ok(usersResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsersResponseDto> createUser(@RequestBody final UsersRequestDto usersRequestDto) {
        UsersResponseDto savedUser = userService.createUser(usersRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") final Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
