package com.t3h.elibrary.repository;

import com.t3h.elibrary.entity.UserInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInforRepository extends JpaRepository<UserInfor, Integer> {
    @Query(value = "SELECT ui.* FROM users_information ui WHERE ui.user_type LIKE ?1", nativeQuery = true)
    public List<UserInfor> listAllStudent(String student);

    @Query(value = "SELECT ui.* FROM users_information ui WHERE ui.username LIKE ?1", nativeQuery = true)
    public UserInfor getStudentByUsername(String username);
}
