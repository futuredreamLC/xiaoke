package com.xiaoke.springboot.controller;


import com.alibaba.fastjson.JSON;
import com.xiaoke.springboot.entity.Product;
import com.xiaoke.springboot.entity.Shoppingcart;
import com.xiaoke.springboot.entity.User;
import com.xiaoke.springboot.service.ShoppingcartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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


    @GetMapping("myshopcart")
    public String toshopcart(HttpSession session, Map<String, Object> map) {
        User user = (User) session.getAttribute("Login");
        if (user != null) {
            Integer id=user.getUserId();
            List<Shoppingcart> carts = shoppingcartService.queryByUid(id);
            session.setAttribute("carts", carts);
            return "myshopcart";
        } else {
            map.put("msg", "请先登录!!!!");
            return "index";
        }
    }

    /**
     * 将某件商品加入购物车中
     *
     * @param shoppingcart
     * @param session
     * @param map
     * @return
     */
    @PostMapping("addtocart")
    public String addtocart(Shoppingcart shoppingcart, HttpSession session, Map<String, Object> map) {
        User user = (User) session.getAttribute("Login");
        Product product = (Product) session.getAttribute("product");
        if (user != null) {
            Shoppingcart shoppingcart1=shoppingcartService.queryByProId(product.getProId());
            if (shoppingcart1==null) {
                Integer uid = user.getUserId();
                Double total = product.getPrice() * shoppingcart.getQuantity();
                shoppingcart.setUserId(uid);
                shoppingcart.setTotal(total);
                shoppingcartService.insert(shoppingcart);
                map.put("msg", "商品已加入购物车，请回我的购物车界面查看");
                return "index";
            } else {
                Integer newQuantity=shoppingcart1.getQuantity()+shoppingcart.getQuantity();
                Integer cartId=shoppingcart1.getCartId();
                Double newTotal=product.getPrice() * newQuantity;
                shoppingcart.setQuantity(newQuantity);
                shoppingcart.setTotal(newTotal);
                shoppingcart.setCartId(cartId);
                shoppingcartService.update(shoppingcart);
                map.put("msg","商品已存在，已将购物车中该商品进行数量叠加，请回购物车查看");
                return "index";
            }
        } else {
            map.put("msg", "商品添加失败，请先登录");
            return "index";
        }
    }

    /**
     * 根据购物车id删除购物车中的某一个商品
     * @param cartId 购物车Id
     * @param map
     * @return
     */
    @PostMapping("delete/{cartId}")
    public String deleteOne(@PathVariable("cartId") Integer cartId,Map<String,Object> map){
        shoppingcartService.deleteById(cartId);
        map.put("msg","该商品已移除你的购物车");
        return "redirect:/shoppingcart/myshopcart";
    }

    @PostMapping("deleteSomeCart")
    public String deleteSome(String deleteInfo,Map<String,Object> map){
        List<Shoppingcart> list=JSON.parseArray(deleteInfo,Shoppingcart.class);
        if (list.size()!=0) {
            Iterator<Shoppingcart> its = list.iterator();
            while (its.hasNext()) {
                Shoppingcart it = its.next();
                int cartId = it.getCartId();
                shoppingcartService.deleteById(cartId);
            }
            return "redirect:/shoppingcart/myshopcart";
        }else {
            map.put("msg","请先选择你要删除的商品");
            return "myshopcart";
        }
    }
}