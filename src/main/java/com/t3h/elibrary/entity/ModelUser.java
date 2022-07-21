package com.t3h.elibrary.entity;

import lombok.*;
import javax.persistence.*;
@Data
@Entity
@Getter
@Setter
@Table(name = "users_information")
public class ModelUser {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "username")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
}
