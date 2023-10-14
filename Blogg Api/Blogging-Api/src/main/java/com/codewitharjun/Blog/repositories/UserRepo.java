package com.codewitharjun.Blog.repositories;

import com.codewitharjun.Blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
