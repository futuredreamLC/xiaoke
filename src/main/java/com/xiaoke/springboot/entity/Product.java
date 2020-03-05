package com.xiaoke.springboot.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2020-03-05 16:49:41
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 893501025432205356L;
    
    private Integer proId;
    
    private Integer typeId;
    
    private String proName;
    
    private Double price;
    
    private String proImage;
    
    private Date proDate;
    
    private Integer sales;
    
    private Integer stocks;
    
    private String proDescribe;


    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public Date getProDate() {
        return proDate;
    }

    public void setProDate(Date proDate) {
        this.proDate = proDate;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public String getProDescribe() {
        return proDescribe;
    }

    public void setProDescribe(String proDescribe) {
        this.proDescribe = proDescribe;
    }

}