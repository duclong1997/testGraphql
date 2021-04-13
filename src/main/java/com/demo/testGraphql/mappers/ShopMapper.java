package com.demo.testGraphql.mappers;

import com.demo.testGraphql.models.dtos.ShopDto;
import com.demo.testGraphql.models.entities.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ShopMapper extends EntityMapper<ShopDto, Shop> {
}
