package com.tonpower.service;

import com.github.pagehelper.PageInfo;
import com.tonpower.domain.ProductInfo;
import com.tonpower.vo.ProductSearchVo;

import java.util.List;

public interface ProductInfoService {
    List<ProductInfo> getAll();

    // 分页功能实现
    PageInfo pageList(int pageNum,int pageSize,ProductSearchVo vo);
    PageInfo pageList(int pageNum,int pageSize);

    int save(ProductInfo info);

    // 根据id查单条
    ProductInfo getById(int pid);

    // 更新商品信息
    int update(ProductInfo info);

    // 删除商品信息
    int delete(Integer pid);

    // 多条件查询
    List<ProductInfo> getProductByCondition(ProductSearchVo vo);
}
