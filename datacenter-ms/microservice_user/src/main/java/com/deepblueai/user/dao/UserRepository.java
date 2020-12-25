package com.deepblueai.user.dao;

import java.util.List;

import com.deepblueai.user.entity.SDKUser;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<SDKUser, Integer> {
    SDKUser findByUserName(String userName);
    List<SDKUser> findByUserNameLike(String userName);
}
