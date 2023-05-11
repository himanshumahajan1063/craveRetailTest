package com.example.craveRetail.service;

import com.example.craveRetail.controller.dto.UsersRequestDto;
import com.example.craveRetail.controller.dto.UsersResponseDto;
import com.example.craveRetail.entity.Users;

import java.util.List;

public interface UserService {
    List<UsersResponseDto> getAllUsers();

    UsersResponseDto getUserById(Long id);

    UsersResponseDto createUser(UsersRequestDto usersRequestDto);

    void deleteUser(Long id);

    void deleteAllUsers();
}
