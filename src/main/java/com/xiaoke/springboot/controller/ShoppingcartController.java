package com.xiaoke.springboot.controller;

import com.xiaoke.springboot.entity.Product;
import com.xiaoke.springboot.entity.Shoppingcart;
import com.xiaoke.springboot.entity.User;
import com.xiaoke.springboot.service.ShoppingcartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
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
                Integer oldQuantity=shoppingcart1.getQuantity();
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

}