package com.xiaoke.springboot.controller;

import com.xiaoke.springboot.entity.Shoppingcart;
import com.xiaoke.springboot.service.ShoppingcartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Shoppingcart)表控制层
 *
 * @author makejava
 * @since 2020-03-02 12:39:50
 */
@Controller
@RequestMapping("shoppingcart")
public class ShoppingcartController {
    /**
     * 服务对象
     */
    @Resource
    private ShoppingcartService shoppingcartService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Shoppingcart selectOne(Integer id) {
        return this.shoppingcartService.queryById(id);
    }

}