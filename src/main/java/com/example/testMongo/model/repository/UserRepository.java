package com.example.testMongo.model.repository;

import com.example.testMongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'auth.login':'?0'}")
    Optional<User> findByLogin(String login);

    @Query("{auth.login:'?0', auth.password: '?1'}")
    List<User> findByLoginAndPassword(String login, String password);

    public long count();
}
