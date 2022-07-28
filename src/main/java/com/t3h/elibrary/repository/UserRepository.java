package com.t3h.elibrary.repository;

import com.t3h.elibrary.entity.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<ModelUser, String> {
    List<ModelUser> findByUsernameLikeIgnoreCase(String username);
}