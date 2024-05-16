package com.example.testMongo.service;

import com.example.testMongo.Mapper.UserMapper;
import com.example.testMongo.config.PasswordConfig;
import com.example.testMongo.dto.AuthDto;
import com.example.testMongo.dto.CredentialDto;
import com.example.testMongo.dto.UserDto;
import com.example.testMongo.model.User;
import com.example.testMongo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordConfig.Encoder passwordEncoder;

    public AuthDto auth(CredentialDto credentialDto) {
        String password = passwordEncoder.encrypt(credentialDto.password());
        Optional<User> optionalUser = userRepository.findByLogin(credentialDto.login());

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.getAuth().getPassword().equals(password) && user.isActive())
                return new AuthDto("connected", true);
            else {
                user.getAuth().addLostConnexion();
                if(user.getAuth().getLostConnexion() == 3)
                    user.setActive(false);
                userRepository.save(user);
                return new AuthDto("user not found", false);
            }
        }
        return new AuthDto("user not found", false);
    }

    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByLogin(userDto.login());

        if(optionalUser.isPresent())
            return null;

        User user = UserMapper.toUser(userDto);

        userRepository.save(user);

        return UserMapper.toDto(user);
    }
}
