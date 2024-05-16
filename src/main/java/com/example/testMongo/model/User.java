package com.example.testMongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document("user")
public class User {
    @Id
    private String id;
    private String name;
    private Date birthday;
    private Auth auth;
    private boolean active;

    public User(String name, Date birthday, Auth auth) {
        this.name = name;
        this.birthday = birthday;
        this.auth = auth;
        this.active = true;
    }
}
