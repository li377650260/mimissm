package com.tonpower.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tonpower.dao.ProductInfoMapper;
import com.tonpower.domain.ProductInfo;
import com.tonpower.domain.ProductInfoExample;
import com.tonpower.service.ProductInfoService;
import com.tonpower.vo.ProductSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: li377650260
 * @date: 2021/8/18 19:52
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {

        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo pageList(int pageNum, int pageSize,ProductSearchVo vo) {
        // 分页插件使用PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum,pageSize);

        List<ProductInfo> list = productInfoMapper.getProductByCondition(vo);
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @Override
    public PageInfo pageList(int pageNum, int pageSize) {
        // 分页插件使用PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum,pageSize);
        ProductInfoExample example = new ProductInfoExample();
        example.setOrderByClause("p_date desc");
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(ProductInfo info) {
        int result = productInfoMapper.insert(info);
        return result;
    }

    @Override
    public ProductInfo getById(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(ProductInfo info) {

        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int delete(Integer pid) {

        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public List<ProductInfo> getProductByCondition(ProductSearchVo vo) {
        return productInfoMapper.getProductByCondition(vo);
    }
}
