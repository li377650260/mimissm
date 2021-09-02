package com.tonpower.vo;

/**
 * @description:
 * @author: li377650260
 * @date: 2021/8/20 20:16
 */
public class ProductSearchVo {
    // 商品名称
    private String name;
    // 商品类型
    private Integer type;
    // 最高价格
    private Integer lprice;
    // 最低价格
    private Integer hprice;

    public ProductSearchVo() {
    }

    public ProductSearchVo(String name, Integer type, Integer lprice, Integer hprice) {
        this.name = name;
        this.type = type;
        this.lprice = lprice;
        this.hprice = hprice;
    }

    @Override
    public String toString() {
        return "ProductSearchVo{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", lprice=" + lprice +
                ", hprice=" + hprice +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLprice() {
        return lprice;
    }

    public void setLprice(Integer lprice) {
        this.lprice = lprice;
    }

    public Integer getHprice() {
        return hprice;
    }

    public void setHprice(Integer hprice) {
        this.hprice = hprice;
    }
}
