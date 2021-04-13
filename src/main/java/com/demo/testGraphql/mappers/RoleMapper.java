package com.demo.testGraphql.mappers;

import com.demo.testGraphql.models.dtos.RoleDto;
import com.demo.testGraphql.models.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleMapper extends EntityMapper<RoleDto, Role> {
}
