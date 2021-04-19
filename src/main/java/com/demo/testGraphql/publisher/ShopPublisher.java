package com.demo.testGraphql.publisher;

import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.models.dtos.ShopDto;
import org.reactivestreams.Publisher;

public interface ShopPublisher {

    public void publish(ShopDto shopDto);

    public Publisher<ShopDto> getShopPublisherForId(Long id);

    public Publisher<ShopDto> getShopPublisher();
}
