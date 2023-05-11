package com.example.craveRetail.Utils;

import com.example.craveRetail.controller.dto.UsersRequestDto;
import com.example.craveRetail.controller.dto.UsersResponseDto;
import com.example.craveRetail.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class MapperUtil {
    public static Users convertToUserEntity(final UsersRequestDto usersRequestDto) {

        return Users.builder()
                .age(usersRequestDto.getAge())
                .name(usersRequestDto.getName()).build();
    }

    public static UsersResponseDto convertToUserResponse(final Users users) {

        return UsersResponseDto.builder()
                .age(users.getAge())
                .name(users.getName()).build();
    }

    public static List<UsersResponseDto> convertToUserResponseList(final List<Users> users) {
        List<UsersResponseDto> usersResponseDtoList = new ArrayList<>();
        for (Users user : users) {
            usersResponseDtoList.add(UsersResponseDto.builder()
                    .age(user.getAge())
                    .name(user.getName()).build());
        }
        return usersResponseDtoList;
    }
}
