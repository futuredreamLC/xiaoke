package com.xiaoke.springboot.service;

import com.xiaoke.springboot.entity.OrdersInfo;
import java.util.List;

/**
 * (OrdersInfo)表服务接口
 *
 * @author makejava
 * @since 2020-03-28 16:01:03
 */
public interface OrdersInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderInfoId 主键
     * @return 实例对象
     */
    OrdersInfo queryById(Integer orderInfoId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrdersInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ordersInfo 实例对象
     * @return 实例对象
     */
    OrdersInfo insert(OrdersInfo ordersInfo);

    /**
     * 修改数据
     *
     * @param ordersInfo 实例对象
     * @return 实例对象
     */
    OrdersInfo update(OrdersInfo ordersInfo);

    /**
     * 通过主键删除数据
     *
     * @param orderInfoId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer orderInfoId);

}