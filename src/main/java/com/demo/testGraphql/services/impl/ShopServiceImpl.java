package com.demo.testGraphql.services.impl;

import com.demo.testGraphql.mappers.ShopMapper;
import com.demo.testGraphql.models.dtos.ShopDto;
import com.demo.testGraphql.models.dtos.ShopIn;
import com.demo.testGraphql.models.entities.Shop;
import com.demo.testGraphql.publisher.ShopPublisher;
import com.demo.testGraphql.repositories.ShopRepository;
import com.demo.testGraphql.services.ShopService;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ShopPublisher shopPublisher;

    @Override
    public ShopDto shopDetail(Long id) {
        return null;
    }

    @Override
    public List<ShopDto> getShops(Integer page, Integer size) {
        return null;
    }

    @Override
    public ShopDto createShop(ShopIn in) {
        Shop shop = new Shop();
        shop.setAddress(in.getAddress());
        shop.setName(in.getName());
        shop = shopRepository.save(shop);
        // push notify
        var shopDto = shopMapper.entityToDto(shop);
        shopPublisher.publish(shopDto);
        return shopDto;
    }

    @Override
    public ShopDto updateShop(Long id, ShopIn in) {

        if (id == null) {
            throw new GraphQLException("Shop not exist");
        }
        Shop shop = new Shop();
        shop.setName(in.getName());
        shop.setAddress(in.getAddress());
        shop = shopRepository.save(shop);
        return shopMapper.entityToDto(shop);
    }

}
