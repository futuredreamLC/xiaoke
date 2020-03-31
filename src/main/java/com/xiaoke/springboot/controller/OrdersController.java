package com.xiaoke.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.xiaoke.springboot.entity.Orders;
import com.xiaoke.springboot.entity.Shoppingcart;
import com.xiaoke.springboot.entity.User;
import com.xiaoke.springboot.service.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
        List<Shoppingcart> list= JSON.parseArray(ordersInfo,Shoppingcart.class);//将前端返回的json字符串转义成shoppingcart对象
        if (list.size()!=0) {
            model.addAttribute("carts", list);
            model.addAttribute("cost",cost);
            model.addAttribute("orderInfo",ordersInfo);
            return "addOrders";
        }else {
            map.put("msg","请先选择您要购买的商品");
            return "myshopcart";
        }
    }

    /**
     * 添加订单，插入数据库
     * @param orders
     * @param orderInfo
     * @param map
     * @return
     */
    @PostMapping("add")
    public String add(Orders orders,String orderInfo,Map<String,Object> map){
        List<Shoppingcart> list= JSON.parseArray(orderInfo,Shoppingcart.class);
        ordersService.insert(orders,list);
        map.put("msg","订单已生成，请前往我的订单，去付款并让商家发货");
        return "redirect:/orders/userOrders";
    }

    /**
     * 查询该用户的订单
     * @param session
     * @param model
     * @return
     */
    @GetMapping("userOrders")
    public String userOrders(HttpSession session,Model model){
        User user=(User) session.getAttribute("Login");
        if (user!=null){
            List<Orders> orders=ordersService.queryByUserId(user.getUserId());
            model.addAttribute("orders",orders);
            model.addAttribute("active","全部");
            return "myOrders";
        }else {
            return "index";
        }
    }


    /**
     * 更新订单的状态
     * @param operateId
     * @param ordersId
     * @return
     */
    @PostMapping("update")
    public String update(@RequestParam("operateId") Integer operateId,
                         @RequestParam("ordersId") String ordersId){
        Orders orders=ordersService.queryById(ordersId);
        ordersService.update(orders,operateId);
        if (operateId!=2){
            return "redirect:/orders/userOrders";
        }else {
            return "redirect:/orders/allOrders";
        }
    }

    /**
     * 查询所有的订单信息
     * @return
     */
    @GetMapping("allOrders")
    public String allOrders(Model model){
        List<Orders> orders=ordersService.queryAllOrd();
        model.addAttribute("orders",orders);
        return "allOrders";
    }

    /**
     * 根据不同的要求查询不同状态的订单信息
     * @param session
     * @param model
     * @return
     */
    @GetMapping("userOrders/{operateId}")
    public String myOrders(@PathVariable("operateId") Integer operateId,HttpSession session,Model model){
        User user=(User) session.getAttribute("Login");
        if (user!=null){
            List<Orders> orders=ordersService.querySomeOrd(user.getUserId(),operateId);
            model.addAttribute("orders",orders);
            if (operateId==1){
                model.addAttribute("active","待支付");
            }
            if (operateId==2){
                model.addAttribute("active","待发货");
            }
            if (operateId==3){
                model.addAttribute("active","待收货");
            }
            if (operateId==4){
                model.addAttribute("active","待评价");
            }
            return "myOrders";
        }else {
            return "index";
        }
    }

}