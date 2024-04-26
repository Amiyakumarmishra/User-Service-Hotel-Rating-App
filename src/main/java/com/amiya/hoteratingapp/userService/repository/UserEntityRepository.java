package com.amiya.hoteratingapp.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amiya.hoteratingapp.userService.entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,String>{

}
