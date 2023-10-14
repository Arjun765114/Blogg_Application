package com.codewitharjun.Blog.services;

import com.codewitharjun.Blog.payloads.UserDto;
import com.codewitharjun.Blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.codewitharjun.Blog.entities.*;
import com.codewitharjun.Blog.exceptions.*;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
       User savedUser = userRepo.save(user);
       return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userid) {
        User user = userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User","Id",userid));
       user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
       user.setPassword(userDto.getPassword());
       user.setAbout(userDto.getAbout());
        User updateUser = userRepo.save(user);
        return userToDto(updateUser);
    }

    @Override
    public UserDto getUserById(Integer userid) {
        User user = userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User","Id",userid));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
      List<User>users= userRepo.findAll();
      List<UserDto> userDtos=  users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
      return userDtos;
    }

    @Override
    public void deleteUSer(Integer id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
        userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        User user= new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
