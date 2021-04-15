package com.demo.testGraphql.mappers;

import com.demo.testGraphql.models.dtos.ShopDto;
import com.demo.testGraphql.models.entities.Shop;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-15T21:37:36+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class ShopMapperImpl implements ShopMapper {

    @Override
    public Shop dtoToEntity(ShopDto dto) {
        if ( dto == null ) {
            return null;
        }

        Shop shop = new Shop();

        if ( dto.getId() != null ) {
            shop.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            shop.setName( dto.getName() );
        }
        if ( dto.getAddress() != null ) {
            shop.setAddress( dto.getAddress() );
        }

        return shop;
    }

    @Override
    public ShopDto entityToDto(Shop entity) {
        if ( entity == null ) {
            return null;
        }

        ShopDto shopDto = new ShopDto();

        if ( entity.getId() != null ) {
            shopDto.setId( entity.getId() );
        }
        if ( entity.getName() != null ) {
            shopDto.setName( entity.getName() );
        }
        if ( entity.getAddress() != null ) {
            shopDto.setAddress( entity.getAddress() );
        }

        return shopDto;
    }

    @Override
    public List<Shop> dtosToEntities(List<ShopDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Shop> list = new ArrayList<Shop>( dtos.size() );
        for ( ShopDto shopDto : dtos ) {
            list.add( dtoToEntity( shopDto ) );
        }

        return list;
    }

    @Override
    public List<ShopDto> entitiesToDtos(List<Shop> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ShopDto> list = new ArrayList<ShopDto>( entities.size() );
        for ( Shop shop : entities ) {
            list.add( entityToDto( shop ) );
        }

        return list;
    }
}
