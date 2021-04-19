package com.demo.testGraphql.mappers;

import com.demo.testGraphql.models.dtos.RoleDto;
import com.demo.testGraphql.models.entities.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-19T21:38:25+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role dtoToEntity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        if ( dto.getId() != null ) {
            role.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            role.setName( dto.getName() );
        }

        return role;
    }

    @Override
    public RoleDto entityToDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        if ( entity.getId() != null ) {
            roleDto.setId( entity.getId() );
        }
        if ( entity.getName() != null ) {
            roleDto.setName( entity.getName() );
        }

        return roleDto;
    }

    @Override
    public List<Role> dtosToEntities(List<RoleDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( dtos.size() );
        for ( RoleDto roleDto : dtos ) {
            list.add( dtoToEntity( roleDto ) );
        }

        return list;
    }

    @Override
    public List<RoleDto> entitiesToDtos(List<Role> entities) {
        if ( entities == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( entities.size() );
        for ( Role role : entities ) {
            list.add( entityToDto( role ) );
        }

        return list;
    }
}
