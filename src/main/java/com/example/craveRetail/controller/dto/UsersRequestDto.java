package com.example.craveRetail.controller.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersRequestDto {
    private String name;
    private int age;
}
