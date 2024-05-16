package com.example.testMongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Auth {
    private int lostConnexion;
    private String login;
    private String password;

    public void addLostConnexion() {
        this.lostConnexion += 1;
    }
}
