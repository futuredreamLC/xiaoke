package com.xiaoke.springboot.service;

import com.xiaoke.springboot.entity.Shoppingcart;
import java.util.List;

/**
 * (Shoppingcart)表服务接口
 *
 * @author makejava
 * @since 2020-03-02 12:39:48
 */
public interface ShoppingcartService {

    /**
     * 通过ID查询单条数据
     *
     * @param cartId 主键
     * @return 实例对象
     */
    Shoppingcart queryById(Integer cartId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Shoppingcart> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param shoppingcart 实例对象
     * @return 实例对象
     */
    Shoppingcart insert(Shoppingcart shoppingcart);

    /**
     * 修改数据
     *
     * @param shoppingcart 实例对象
     * @return 实例对象
     */
    Shoppingcart update(Shoppingcart shoppingcart);

    /**
     * 通过主键删除数据
     *
     * @param cartId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer cartId);

    /**
     * 通过用户id查询出用户购物车中所有的商品
     * @param Uid
     * @return
     */
    List<Shoppingcart> queryByUid(Integer Uid);

    /**
     * 通过产品id查询购物车中是否有这个产品
     * @param proId
     * @return
     */
    Shoppingcart queryByProId(Integer proId);
}