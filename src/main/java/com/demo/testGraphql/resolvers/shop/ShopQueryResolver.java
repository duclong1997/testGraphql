package com.demo.testGraphql.resolvers.shop;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.testGraphql.models.dtos.ShopDto;
import com.demo.testGraphql.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShopQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private ShopService shopService;

    public ShopDto shopDetail(Long id){
        return shopService.shopDetail(id);
    }

    public List<ShopDto> getShops(Integer page, Integer size){
        return  shopService.getShops(page, size);
    }

}
