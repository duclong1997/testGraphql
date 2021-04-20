package com.demo.testGraphql.resolvers.shop;

import com.demo.testGraphql.models.dtos.ShopDto;
import com.demo.testGraphql.models.dtos.ShopIn;
import com.demo.testGraphql.services.ShopService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShopMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private ShopService shopService;

    @PreAuthorize("isAuthenticated()")
    public ShopDto createShop(ShopIn in) {
        return shopService.createShop(in);
    }

    @PreAuthorize("isAuthenticated()")
    public ShopDto updateShop(Long id, ShopIn in) {
        return shopService.updateShop(id, in);
    }
}
