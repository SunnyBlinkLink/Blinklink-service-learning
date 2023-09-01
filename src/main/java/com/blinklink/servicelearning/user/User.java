package com.blinklink.servicelearning.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "bl_user")
@NoArgsConstructor(force = true)
public class User {

    @Id
    private UUID Id;
    private String name;
    private String email;
    private String mobile;

    public User(String name,String email,String mobile){
        this.Id=UUID.randomUUID();
        this.name=name;
        this.email=email;
        this.mobile=mobile;
    }
}
