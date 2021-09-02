package com.tonpower;

import com.tonpower.dao.ProductInfoMapper;
import com.tonpower.domain.ProductInfo;
import com.tonpower.utils.MD5Util;
import com.tonpower.utils.SqlSessionUtil;
import com.tonpower.vo.ProductSearchVo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.List;

/**
 * @description:
 * @author: li377650260
 * @date: 2021/8/18 17:18
 */
public class Test {
    @org.junit.Test
    public void test01(){
        String password = "000000";
        System.out.println(MD5Util.getMD5(password));
    }
    @org.junit.Test
    public void test02(){
        ProductSearchVo vo = new ProductSearchVo();
        vo.setName("5");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_dao.xml");
        ProductInfoMapper mapper = (ProductInfoMapper) context.getBean("productInfoMapper");
        List<ProductInfo> list = mapper.getProductByCondition(vo);
        for (ProductInfo info: list){
            System.out.println(info);
        }
    }
}
