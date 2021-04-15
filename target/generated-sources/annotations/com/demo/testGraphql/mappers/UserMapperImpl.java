package com.demo.testGraphql.mappers;

import com.demo.testGraphql.models.dtos.RoleDto;
import com.demo.testGraphql.models.dtos.UserDto;
import com.demo.testGraphql.models.entities.Role;
import com.demo.testGraphql.models.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-15T21:49:20+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User dtoToEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        if ( dto.getId() != null ) {
            user.setId( dto.getId() );
        }
        if ( dto.getUsername() != null ) {
            user.setUsername( dto.getUsername() );
        }
        if ( dto.getFirstname() != null ) {
            user.setFirstname( dto.getFirstname() );
        }
        if ( dto.getLastname() != null ) {
            user.setLastname( dto.getLastname() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getEnabled() != null ) {
            user.setEnabled( dto.getEnabled() );
        }
        if ( dto.getRole() != null ) {
            user.setRole( roleDtoToRole( dto.getRole() ) );
        }

        return user;
    }

    @Override
    public UserDto entityToDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        if ( entity.getId() != null ) {
            userDto.setId( entity.getId() );
        }
        if ( entity.getUsername() != null ) {
            userDto.setUsername( entity.getUsername() );
        }
        if ( entity.getFirstname() != null ) {
            userDto.setFirstname( entity.getFirstname() );
        }
        if ( entity.getLastname() != null ) {
            userDto.setLastname( entity.getLastname() );
        }
        if ( entity.getEmail() != null ) {
            userDto.setEmail( entity.getEmail() );
        }
        if ( entity.getEnabled() != null ) {
            userDto.setEnabled( entity.getEnabled() );
        }
        if ( entity.getRole() != null ) {
            userDto.setRole( roleToRoleDto( entity.getRole() ) );
        }

        return userDto;
    }

    @Override
    public List<User> dtosToEntities(List<UserDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtos.size() );
        for ( UserDto userDto : dtos ) {
            list.add( dtoToEntity( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> entitiesToDtos(List<User> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entities.size() );
        for ( User user : entities ) {
            list.add( entityToDto( user ) );
        }

        return list;
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        if ( roleDto.getId() != null ) {
            role.setId( roleDto.getId() );
        }
        if ( roleDto.getName() != null ) {
            role.setName( roleDto.getName() );
        }

        return role;
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        if ( role.getId() != null ) {
            roleDto.setId( role.getId() );
        }
        if ( role.getName() != null ) {
            roleDto.setName( role.getName() );
        }

        return roleDto;
    }
}
