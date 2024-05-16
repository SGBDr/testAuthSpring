package com.example.testMongo.worker;

import com.example.testMongo.model.User;
import com.example.testMongo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class UserWoker implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;

    public void start() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("One turn in worker....");
            System.out.println(this.userRepository.findAll().size() + " Users");

            for(User user : this.userRepository.findAll()){
                if(user.getAuth().getLostConnexion() >= 3 && !user.isActive()) {
                    System.out.println("Detect value of " + user);
                    user.setActive(true);
                    user.getAuth().setLostConnexion(0);
                    userRepository.save(user);
                    System.out.println("Change value of " + user);
                }
            }
        }, 0, 10, TimeUnit.SECONDS);

    }

    @Override
    public void run(ApplicationArguments args) {
        start();
    }
}
