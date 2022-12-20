package com.t3h.elibrary.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "users_information")
public class ModelUser {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
}
