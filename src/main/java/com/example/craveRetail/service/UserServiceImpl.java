package com.example.craveRetail.service;

import com.example.craveRetail.CraveRetailApplication;
import com.example.craveRetail.Utils.MapperUtil;
import com.example.craveRetail.controller.dto.UsersRequestDto;
import com.example.craveRetail.controller.dto.UsersResponseDto;
import com.example.craveRetail.entity.Users;
import com.example.craveRetail.errorHandling.exception.CraveRetailServiceException;
import com.example.craveRetail.errorHandling.exception.UserNotFoundException;
import com.example.craveRetail.repository.UserRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(CraveRetailApplication.class);
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UsersResponseDto> getAllUsers() {
        try {
            Optional<List<Users>> optionalUsersList = Optional.of(userRepository.findAll());
            logger.info("Fetching all users from the database");
            return MapperUtil.convertToUserResponseList(optionalUsersList.get());
        } catch (Exception ex) {
            logger.error("Error occurred while performing get all users calls. Failed with error :" + ex.getMessage());
            throw new CraveRetailServiceException();
        }
    }

    @Override
    @Cacheable("users")
    public UsersResponseDto getUserById(final Long id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isEmpty()) {
            logger.error("User could not be found for id" + id);
            throw new UserNotFoundException();
        }
        logger.info("Fetching users details from the database for id : " + id);
        return MapperUtil.convertToUserResponse(optionalUsers.get());
    }

    @Override
    public UsersResponseDto createUser(final UsersRequestDto usersRequestDto) {
        try {
            Users users = MapperUtil.convertToUserEntity(usersRequestDto);
            Optional<Users> optionalUsers = Optional.of(userRepository.save(users));
            logger.info("Successfully created user with name" + optionalUsers.get().getName());
            return MapperUtil.convertToUserResponse(optionalUsers.get());
        } catch (Exception ex) {
            logger.error("Error occurred while performing create user action. Failed with error :" + ex.getMessage());
            throw new CraveRetailServiceException();
        }
    }

    @Override
    public void deleteUser(final Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EntityNotFoundException | EmptyResultDataAccessException e) {
            logger.error("Delete user operation failed. No user exists in db for id :" + id);
            throw new UserNotFoundException();
        } catch (Exception ex) {
            logger.error("Error occurred while deleting user. Failed with error :" + ex.getMessage());
            throw new CraveRetailServiceException();
        }
    }

    @Override
    public void deleteAllUsers() {
        try {
            userRepository.deleteAll();
        } catch (Exception ex) {
            logger.error("Error occurred while deleting all users. Failed with error :" + ex.getMessage());
            throw new CraveRetailServiceException();
        }
    }
}
