package com.xiaoke.springboot.service.impl;

import com.xiaoke.springboot.dao.OrdersInfoDao;
import com.xiaoke.springboot.dao.ShoppingcartDao;
import com.xiaoke.springboot.entity.Orders;
import com.xiaoke.springboot.dao.OrdersDao;
import com.xiaoke.springboot.entity.OrdersInfo;
import com.xiaoke.springboot.entity.Shoppingcart;
import com.xiaoke.springboot.service.OrdersInfoService;
import com.xiaoke.springboot.service.OrdersService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * (Orders)表服务实现类
 *
 * @author makejava
 * @since 2020-03-27 12:26:08
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersDao ordersDao;


    @Resource
    private OrdersInfoDao ordersInfoDao;

    @Resource
    private ShoppingcartDao shoppingcartDao;


    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public Orders queryById(String orderId) {
        return this.ordersDao.queryById(orderId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Orders> queryAllByLimit(int offset, int limit) {
        return this.ordersDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @param list
     * @return 实例对象
     */
    @Override
    public Orders insert(Orders orders, List<Shoppingcart> list) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHMM");
        String date=dateFormat.format(System.currentTimeMillis());
        int r=(int) Math.random()*100;
        String ordersId=date+r;
        orders.setOrderId(ordersId);
        orders.setOrderDate(new Date());
        orders.setStatus("待支付");
        Iterator<Shoppingcart> its=list.iterator();
        while (its.hasNext()){
            Shoppingcart it=its.next();
            OrdersInfo ordersInfo=new OrdersInfo();
            ordersInfo.setImg(it.getProduct().getProImage());
            ordersInfo.setOrderId(ordersId);
            ordersInfo.setPrice(it.getProduct().getPrice());
            ordersInfo.setProId(it.getProId());
            ordersInfo.setQuantity(it.getQuantity());
            ordersInfo.setProName(it.getProduct().getProName());
            ordersInfoDao.insert(ordersInfo);
            shoppingcartDao.deleteById(it.getCartId());
        }
        this.ordersDao.insert(orders);
        return orders;
    }

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Override
    public Orders update(Orders orders) {
        this.ordersDao.update(orders);
        return this.queryById(orders.getOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String orderId) {
        return this.ordersDao.deleteById(orderId) > 0;
    }
}