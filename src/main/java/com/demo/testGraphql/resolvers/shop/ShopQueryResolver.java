package com.demo.testGraphql.resolvers.shop;

import com.demo.testGraphql.models.dtos.ShopDto;
import com.demo.testGraphql.services.ShopService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ShopQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private ShopService shopService;

    @PreAuthorize("isAuthenticated()")
    public ShopDto shopDetail(Long id) {
        return shopService.shopDetail(id);
    }

    @PreAuthorize("isAuthenticated()")
    public List<ShopDto> getShops(Integer page, Integer size) {
        return shopService.getShops(page, size);
    }

}
