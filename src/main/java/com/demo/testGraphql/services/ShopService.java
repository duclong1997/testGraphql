package com.demo.testGraphql.services;

import com.demo.testGraphql.models.dtos.ShopDto;
import com.demo.testGraphql.models.dtos.ShopIn;

import java.util.List;

public interface ShopService {
    ShopDto shopDetail(Long id);

    List<ShopDto> getShops(Integer page, Integer size);

    ShopDto createShop(ShopIn in);

    ShopDto updateShop(Long id, ShopIn in);
}
