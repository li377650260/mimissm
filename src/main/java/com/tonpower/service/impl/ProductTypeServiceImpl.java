package com.tonpower.service.impl;

import com.tonpower.dao.ProductTypeMapper;
import com.tonpower.domain.ProductType;
import com.tonpower.domain.ProductTypeExample;
import com.tonpower.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: li377650260
 * @date: 2021/8/18 22:34
 */

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
