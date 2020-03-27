package com.xiaoke.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.xiaoke.springboot.entity.Orders;
import com.xiaoke.springboot.entity.Shoppingcart;
import com.xiaoke.springboot.service.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Orders)表控制层
 *
 * @author makejava
 * @since 2020-03-27 12:26:09
 */
@Controller
@RequestMapping("orders")
public class OrdersController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Orders selectOne(String id) {
        return this.ordersService.queryById(id);
    }


    /**
     * 根据购物车提交的信息，跳转到确认订单页面
     * @param ordersInfo
     * @param model
     * @return
     */
    @PostMapping("addOrders")
    public String addOrders(String ordersInfo,Double cost, Model model, Map<String,Object> map){
        List<Shoppingcart> list= JSON.parseArray(ordersInfo,Shoppingcart.class);
        if (list.size()!=0) {
            model.addAttribute("carts", list);
            model.addAttribute("cost",cost);
            return "addOrders";
        }else {
            map.put("msg","请先选择您要购买的商品");
            return "myshopcart";
        }
    }

}