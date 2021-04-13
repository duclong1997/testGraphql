package com.demo.testGraphql.mappers;

import com.demo.testGraphql.models.dtos.UserDto;
import com.demo.testGraphql.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper extends EntityMapper<UserDto, User> {
}
