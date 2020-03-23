package com.xiaoke.springboot.entity;

import java.io.Serializable;

/**
 * (Shoppingcart)实体类
 *
 * @author makejava
 * @since 2020-03-02 12:39:45
 */
public class Shoppingcart implements Serializable {
    private static final long serialVersionUID = 315128774140831198L;
    
    private Integer cartId;
    
    private Integer proId;
    
    private Integer userId;
    
    private Integer quantity;
    
    private Double total;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}