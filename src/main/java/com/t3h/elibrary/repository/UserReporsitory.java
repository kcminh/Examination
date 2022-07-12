package com.t3h.elibrary.repository;

import com.t3h.elibrary.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReporsitory extends JpaRepository<UserInfo, Integer> {
    public UserInfo getByEmail(String email);
    public UserInfo getByUserId(int userId);
}
