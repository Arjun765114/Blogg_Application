package com.codewitharjun.Blog.services;

import com.codewitharjun.Blog.entities.User;
import com.codewitharjun.Blog.payloads.UserDto;

import java.util.List;

public interface UserService {

   UserDto createUser(UserDto user);

   UserDto updateUser(UserDto user, Integer id);

   UserDto getUserById(Integer id);

   List<UserDto> getAllUsers();

   void deleteUSer(Integer id);
}
