package com.xiaoke.springboot.service.impl;

import com.xiaoke.springboot.entity.Product;
import com.xiaoke.springboot.entity.Shoppingcart;
import com.xiaoke.springboot.dao.ShoppingcartDao;
import com.xiaoke.springboot.entity.User;
import com.xiaoke.springboot.service.ShoppingcartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * (Shoppingcart)表服务实现类
 *
 * @author makejava
 * @since 2020-03-02 12:39:49
 */
@Service("shoppingcartService")
public class ShoppingcartServiceImpl implements ShoppingcartService {

    @Resource
    private ShoppingcartDao shoppingcartDao;

    @Resource
    private ShoppingcartService shoppingcartService;

    @Resource
    HttpSession session;


    @Resource
    Map<String,Object> map;

    /**
     * 通过ID查询单条数据
     *
     * @param cartId 主键
     * @return 实例对象
     */
    @Override
    public Shoppingcart queryById(Integer cartId) {
        return this.shoppingcartDao.queryById(cartId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Shoppingcart> queryAllByLimit(int offset, int limit) {
        return this.shoppingcartDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param shoppingcart 实例对象
     * @return 实例对象
     */
    @Override
    public Shoppingcart insert(Shoppingcart shoppingcart) {
        User user = (User) session.getAttribute("Login");
        Product product = (Product) session.getAttribute("product");
        Shoppingcart shoppingcart1 = shoppingcartService.queryByProId(product.getProId(),user.getUserId());
        if (shoppingcart1 == null) {
            Integer uid = user.getUserId();
            Double total = product.getPrice() * shoppingcart.getQuantity();
            shoppingcart.setUserId(uid);
            shoppingcart.setTotal(total);
            this.shoppingcartDao.insert(shoppingcart);
            return shoppingcart;
        } else {
            Integer newQuantity = shoppingcart1.getQuantity() + shoppingcart.getQuantity();
            Integer cartId = shoppingcart1.getCartId();
            Double newTotal = product.getPrice() * newQuantity;
            shoppingcart.setQuantity(newQuantity);
            shoppingcart.setTotal(newTotal);
            shoppingcart.setCartId(cartId);
            this.shoppingcartDao.update(shoppingcart);
            return shoppingcart;
        }
    }

    /**
     * 修改数据
     *
     * @param shoppingcart 实例对象
     * @return 实例对象
     */
    @Override
    public Shoppingcart update(Shoppingcart shoppingcart) {
        this.shoppingcartDao.update(shoppingcart);
        return this.queryById(shoppingcart.getCartId());
    }

    /**
     * 通过主键删除数据
     *
     * @param cartId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer cartId) {
        return this.shoppingcartDao.deleteById(cartId) > 0;
    }

    /**
     * 通过用户的id查询出用户购物车中所有的商品
     * @param Uid
     * @return
     */
    @Override
    public List<Shoppingcart> queryByUid(Integer userId) {
        return this.shoppingcartDao.queryByUid(userId);
    }

    /**
     * 根据用户的id和产品的id查询该用户下的购物车中是否已经有了该产品
     * @param proId
     * @param userId
     * @return
     */
    @Override
    public Shoppingcart queryByProId(Integer proId,Integer userId) {
        return this.shoppingcartDao.queryByProId(proId,userId);
    }

    /**
     * 加入一件商品进入购物车
     * @param product
     * @return
     */
    @Override
    public Shoppingcart insertone(Product product) {
        User user = (User) session.getAttribute("Login");
        Shoppingcart shoppingcart=new Shoppingcart();
        Shoppingcart shoppingcart1 = shoppingcartService.queryByProId(product.getProId(),user.getUserId());
        if (shoppingcart1 == null) {
            Integer uid = user.getUserId();
            Integer proId=product.getProId();
            Integer quantity=1;
            Double total = product.getPrice();
            shoppingcart.setProId(proId);
            shoppingcart.setQuantity(quantity);
            shoppingcart.setUserId(uid);
            shoppingcart.setTotal(total);
            this.shoppingcartDao.insert(shoppingcart);
            return shoppingcart;
        } else {
            Integer newQuantity = shoppingcart1.getQuantity() + 1;
            Integer cartId = shoppingcart1.getCartId();
            Double newTotal = product.getPrice() * newQuantity;
            shoppingcart.setQuantity(newQuantity);
            shoppingcart.setTotal(newTotal);
            shoppingcart.setCartId(cartId);
            this.shoppingcartDao.update(shoppingcart);
            return shoppingcart;
        }
    }
}