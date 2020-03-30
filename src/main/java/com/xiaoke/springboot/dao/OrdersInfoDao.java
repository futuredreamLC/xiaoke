package com.xiaoke.springboot.dao;

import com.xiaoke.springboot.entity.OrdersInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (OrdersInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-28 16:01:02
 */
public interface OrdersInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderInfoId 主键
     * @return 实例对象
     */
    OrdersInfo queryById(Integer orderInfoId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrdersInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param ordersInfo 实例对象
     * @return 对象列表
     */
    List<OrdersInfo> queryAll(OrdersInfo ordersInfo);

    /**
     * 新增数据
     *
     * @param ordersInfo 实例对象
     * @return 影响行数
     */
    int insert(OrdersInfo ordersInfo);

    /**
     * 修改数据
     *
     * @param ordersInfo 实例对象
     * @return 影响行数
     */
    int update(OrdersInfo ordersInfo);

    /**
     * 通过主键删除数据
     *
     * @param orderInfoId 主键
     * @return 影响行数
     */
    int deleteById(Integer orderInfoId);

    /**
     * 通过订单id查询订单详情信息
     * @param ordersId
     * @return
     */
    List<OrdersInfo> queryByOrdersId(String ordersId);
}