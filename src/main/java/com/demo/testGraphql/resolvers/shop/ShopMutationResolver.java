package com.demo.testGraphql.resolvers.shop;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.testGraphql.models.dtos.ShopDto;
import com.demo.testGraphql.models.dtos.ShopIn;
import com.demo.testGraphql.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShopMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private ShopService shopService;

    public ShopDto createShop(ShopIn in) {
        return shopService.createShop(in);
    }

    public ShopDto updateShop(Long id, ShopIn in) {
        return shopService.updateShop(id, in);
    }
}
