package com.xiaoke.springboot.dao;

import com.xiaoke.springboot.entity.Orders;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Orders)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 12:26:07
 */
public interface OrdersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    Orders queryById(String orderId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Orders> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orders 实例对象
     * @return 对象列表
     */
    List<Orders> queryAll(Orders orders);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int insert(Orders orders);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int update(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(String orderId);

    /**
     * 通过userId查询该用户下的所有订单
     * @param userId
     * @return
     */
    List<Orders> queryByUserId(Integer userId);

    /**
     * 查询所有的订单信息
     * @return
     */
    List<Orders> queryAllOrd();

    /**
     * 根据条件查询不同状态的订单信息
     * @param userId
     * @param status
     * @return
     */
    List<Orders> querySomeOrd(@Param("userId") Integer userId, @Param("status") String status);
}