package com.demo.testGraphql.publisher.impl;

import com.demo.testGraphql.models.dtos.ShopDto;
import com.demo.testGraphql.publisher.ShopPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShopPublisherImpl extends EntityPublisherImpl<ShopDto> implements ShopPublisher {

    @Override
    public void publish(ShopDto shopDto) {
        this.sink.next(shopDto);
    }

    @Override
    public Publisher<ShopDto> getShopPublisherForId(Long id) {
        return processor.filter(shop -> id.equals(shop.getId())).map(shop -> {
            log.info("publishing book: {}", shop);
            return shop;
        });
    }

    @Override
    public Publisher<ShopDto> getShopPublisher() {
        return processor.map(shop -> {
            log.info("publishing shop : {}", shop);
            return shop;
        });
    }
}
