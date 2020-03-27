package com.xiaoke.springboot.entity;

import java.io.Serializable;

/**
 * (OrdersInfo)实体类
 *
 * @author makejava
 * @since 2020-03-27 10:58:45
 */
public class OrdersInfo implements Serializable {
    private static final long serialVersionUID = 243303187979197086L;
    
    private Integer orderInfoId;
    
    private String orderId;
    
    private Integer proId;
    
    private Double price;
    
    private Integer quantity;
    
    private String img;


    public Integer getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}