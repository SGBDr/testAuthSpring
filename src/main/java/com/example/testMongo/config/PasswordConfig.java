package com.example.testMongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class PasswordConfig {

    public static class Encoder {
        public String encrypt(String value) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                md.update(value.getBytes());
                byte[] digest = md.digest();
                StringBuilder hexString = new StringBuilder();
                for (byte b : digest) {
                    hexString.append(String.format("%02x", b));
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                return value;
            }
        }
    }

    @Bean
    public Encoder encoder() {
        return new Encoder();
    }
}
