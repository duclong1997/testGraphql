package com.demo.testGraphql.services.impl;

import com.demo.testGraphql.mappers.ShopMapper;
import com.demo.testGraphql.models.dtos.ShopDto;
import com.demo.testGraphql.models.dtos.ShopIn;
import com.demo.testGraphql.repositories.ShopRepository;
import com.demo.testGraphql.services.ShopService;
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
        return null;
    }

    @Override
    public ShopDto updateShop(Long id, ShopIn in) {
        return null;
    }

}
