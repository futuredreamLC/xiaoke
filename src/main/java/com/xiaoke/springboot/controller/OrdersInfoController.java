package com.xiaoke.springboot.controller;

import com.xiaoke.springboot.entity.OrdersInfo;
import com.xiaoke.springboot.service.OrdersInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (OrdersInfo)表控制层
 *
 * @author makejava
 * @since 2020-03-28 16:01:05
 */
@Controller
@RequestMapping("ordersInfo")
public class OrdersInfoController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersInfoService ordersInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public OrdersInfo selectOne(Integer id) {
        return this.ordersInfoService.queryById(id);
    }

}