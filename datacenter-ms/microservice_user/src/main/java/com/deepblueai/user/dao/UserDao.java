package com.deepblueai.user.dao;

import com.deepblueai.user.entity.SDKUser;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* 用户Dao
* */
public interface UserDao extends JpaRepository<SDKUser, Integer> {
}
