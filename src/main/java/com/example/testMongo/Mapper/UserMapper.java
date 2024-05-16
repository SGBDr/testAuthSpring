package com.example.testMongo.Mapper;

import com.example.testMongo.config.PasswordConfig;
import com.example.testMongo.dto.UserDto;
import com.example.testMongo.model.Auth;
import com.example.testMongo.model.User;

public class UserMapper {
    private static final PasswordConfig.Encoder passwordEncoder = new PasswordConfig.Encoder();

    public static User toUser(UserDto userDto) {
        String password = passwordEncoder.encrypt(userDto.password());

        Auth auth = new Auth(0, userDto.login(), password);

        return new User(userDto.name(), userDto.birthday(), auth);
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getAuth().getLogin(), null, user.getBirthday());
    }
}
