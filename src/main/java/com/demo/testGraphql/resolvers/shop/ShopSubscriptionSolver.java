package com.demo.testGraphql.resolvers.shop;

import com.demo.testGraphql.models.dtos.ShopDto;
import com.demo.testGraphql.publisher.ShopPublisher;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShopSubscriptionSolver implements GraphQLSubscriptionResolver {

    @Autowired
    private ShopPublisher shopPublisher;

    @PreAuthorize("isAuthenticated()")
    public Publisher<ShopDto> shops() {
        return shopPublisher.getShopPublisher();
    }

    @PreAuthorize("isAuthenticated()")
    public Publisher<ShopDto> shopDetail(Long id) {
        return shopPublisher.getShopPublisherForId(id);
    }
}
