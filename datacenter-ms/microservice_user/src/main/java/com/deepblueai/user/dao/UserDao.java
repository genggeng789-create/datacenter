package com.deepblueai.user.dao;

import com.deepblueai.user.entity.SDKUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
* 用户Dao
* */
public interface UserDao extends JpaRepository<SDKUser, Integer> {
    SDKUser findByUserName(String userName);
    List<SDKUser> findByUserNameLike(String userName);
}
