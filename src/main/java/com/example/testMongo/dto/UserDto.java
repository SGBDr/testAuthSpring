package com.example.testMongo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.Nulls;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(String id, String name, String login, String password, Date birthday) {
}
