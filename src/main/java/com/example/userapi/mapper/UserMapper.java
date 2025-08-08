package com.example.userapi.mapper;

import com.example.userapi.dto.UserCreateDto;
import com.example.userapi.dto.UserDto;
import com.example.userapi.dto.UserUpdateDto;
import com.example.userapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User entity);
    User toEntity(UserCreateDto createDto);
    void updateEntity(UserUpdateDto updateDto, @MappingTarget User entity);
}
