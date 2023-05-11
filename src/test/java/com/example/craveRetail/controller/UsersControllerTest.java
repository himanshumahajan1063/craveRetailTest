package com.example.craveRetail.controller;

import com.example.craveRetail.entity.Users;
import com.example.craveRetail.repository.UserRepository;
import com.example.craveRetail.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private static final String REQUEST_MAPPING = "/crave-retail/users";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test get user by id by passing an id which exists")
    public void testGetUserById() throws Exception {
        Users users = new Users(11L, "John Doe", 30);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(users));

        mockMvc.perform(MockMvcRequestBuilders.get(REQUEST_MAPPING + "/11"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(users.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(users.getAge()));
    }

    @Test
    @DisplayName("Test get user by id by passing id which does not exist.")
    public void testGetUserById_notFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REQUEST_MAPPING + "/112"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Test get all users api")
    public void testGetAllUsers() throws Exception {
        Users user1 = new Users(1L, "John Doe", 30);
        Users user2 = new Users(2L, "Jane Smith", 25);
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        mockMvc.perform(MockMvcRequestBuilders.get(REQUEST_MAPPING))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(user1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(user1.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(user2.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(user2.getAge()));
    }

    @Test
    @DisplayName("Test delete user by id api")
    public void testDeleteUserById() throws Exception {
        Users user = new Users(2L, "John Doe", 30);
        Mockito.doNothing().when(userRepository).deleteById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete(REQUEST_MAPPING + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test delete user by id when user does not exists")
    public void testDeleteUserById_userNotExist() throws Exception {
        Mockito.doThrow(EntityNotFoundException.class).when(userRepository).deleteById(anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete(REQUEST_MAPPING + "/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}