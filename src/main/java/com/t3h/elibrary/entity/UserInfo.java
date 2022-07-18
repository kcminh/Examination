package com.t3h.elibrary.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "users_information")
public class UserInfo {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "create_at")
    private Timestamp createAt;
    @Column(name = "status")
    private String status;
    @Column(name = "expire_date")
    private Date expireDate;

}
