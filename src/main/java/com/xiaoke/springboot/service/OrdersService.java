package com.xiaoke.springboot.service;

import com.xiaoke.springboot.entity.Orders;
import com.xiaoke.springboot.entity.Shoppingcart;

import java.util.List;

/**
 * (Orders)表服务接口
 *
 * @author makejava
 * @since 2020-03-27 12:26:08
 */
public interface OrdersService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    Orders queryById(String orderId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Orders> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @param list
     * @return 实例对象
     */
    Orders insert(Orders orders, List<Shoppingcart> list);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Orders update(Orders orders,Integer operateId);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(String orderId);


    /**
     * 通过用户Id查询该用户所有的订单
     * @param userId
     * @return
     */
    List<Orders> queryByUserId(Integer userId);

    /**
     * 查询所有的订单消息
     * @return
     */
    List<Orders> queryAllOrd();

    /**
     * 通过条件查询该用户不同的订单信息
     * @param userId
     * @param operateId
     * @return
     */
    List<Orders> querySomeOrd(Integer userId, Integer operateId);
}