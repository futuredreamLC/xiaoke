package com.xiaoke.springboot.service.impl;

import com.xiaoke.springboot.entity.OrdersInfo;
import com.xiaoke.springboot.dao.OrdersInfoDao;
import com.xiaoke.springboot.service.OrdersInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (OrdersInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-03-27 10:58:45
 */
@Service("ordersInfoService")
public class OrdersInfoServiceImpl implements OrdersInfoService {
    @Resource
    private OrdersInfoDao ordersInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderInfoId 主键
     * @return 实例对象
     */
    @Override
    public OrdersInfo queryById(Integer orderInfoId) {
        return this.ordersInfoDao.queryById(orderInfoId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OrdersInfo> queryAllByLimit(int offset, int limit) {
        return this.ordersInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param ordersInfo 实例对象
     * @return 实例对象
     */
    @Override
    public OrdersInfo insert(OrdersInfo ordersInfo) {
        this.ordersInfoDao.insert(ordersInfo);
        return ordersInfo;
    }

    /**
     * 修改数据
     *
     * @param ordersInfo 实例对象
     * @return 实例对象
     */
    @Override
    public OrdersInfo update(OrdersInfo ordersInfo) {
        this.ordersInfoDao.update(ordersInfo);
        return this.queryById(ordersInfo.getOrderInfoId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderInfoId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer orderInfoId) {
        return this.ordersInfoDao.deleteById(orderInfoId) > 0;
    }
}