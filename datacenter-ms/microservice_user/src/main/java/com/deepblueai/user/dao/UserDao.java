package com.deepblueai.user.dao;

import com.deepblueai.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* 用户Dao
* */
public interface UserDao extends JpaRepository<User, Integer> {
}
