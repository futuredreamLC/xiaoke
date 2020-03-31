package com.xiaoke.springboot.service.impl;

import com.xiaoke.springboot.dao.OrdersInfoDao;
import com.xiaoke.springboot.dao.ProductDao;
import com.xiaoke.springboot.dao.ShoppingcartDao;
import com.xiaoke.springboot.entity.Orders;
import com.xiaoke.springboot.dao.OrdersDao;
import com.xiaoke.springboot.entity.OrdersInfo;
import com.xiaoke.springboot.entity.Product;
import com.xiaoke.springboot.entity.Shoppingcart;
import com.xiaoke.springboot.service.OrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    private ProductDao productDao;


    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public Orders queryById(String orderId) {
        Orders orders=ordersDao.queryById(orderId);
        List<OrdersInfo> ordersInfos=ordersInfoDao.queryByOrdersId(orders.getOrderId());
        Map<String,List<OrdersInfo>> collect=ordersInfos.stream().collect(Collectors.groupingBy(OrdersInfo::getOrderId));
        List<OrdersInfo> list=collect.get(orders.getOrderId());
        orders.setOrdersInfos(list);
        return orders;
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
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHMMSS");
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
    public Orders update(Orders orders,Integer operateId) {
        if (operateId==1){
            orders.setStatus("待发货");
        }
        if (operateId==2){
            orders.setStatus("待收货");
        }
        if (operateId==3){
            orders.setStatus("待评价");
            List<OrdersInfo> infos=orders.getOrdersInfos();
            Iterator<OrdersInfo> its=infos.iterator();
            while (its.hasNext()){
                OrdersInfo it=its.next();
                Product product=productDao.queryById(it.getProId());
                Integer sales=it.getQuantity()+product.getSales();
                Integer stocks=product.getStocks()-it.getQuantity();
                product.setSales(sales);
                product.setStocks(stocks);
                productDao.update(product);
            }
        }
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


    /**
     * 通过用户Id查询该用户所有的订单
     * @param userId
     * @return
     */
    @Override
    public List<Orders> queryByUserId(Integer userId) {
        List<Orders> orders=ordersDao.queryByUserId(userId);
        Iterator<Orders> its=orders.iterator();
        while (its.hasNext()){
            Orders it=its.next();
            List<OrdersInfo> ordersInfos=ordersInfoDao.queryByOrdersId(it.getOrderId());
            Map<String,List<OrdersInfo>> collect=ordersInfos.stream().collect(Collectors.groupingBy(OrdersInfo::getOrderId));
            List<OrdersInfo> list=collect.get(it.getOrderId());
            it.setOrdersInfos(list);
        }
        return orders;
    }

    /**
     * 拆查询所有的订单信息
     * @return
     */
    @Override
    public List<Orders> queryAllOrd() {
        List<Orders> orders=ordersDao.queryAllOrd();
        Iterator<Orders> ords=orders.iterator();
        while (ords.hasNext()){
            Orders it=ords.next();
            List<OrdersInfo> ordersInfos=ordersInfoDao.queryByOrdersId(it.getOrderId());
            Map<String,List<OrdersInfo>> collect=ordersInfos.stream().collect(Collectors.groupingBy(OrdersInfo::getOrderId));
            List<OrdersInfo> list=collect.get(it.getOrderId());
            it.setOrdersInfos(list);
        }
        return orders;
    }

    /**
     * 通过条件查询用户不同状态订单的信息
     * @param userId
     * @param operateId
     * @return
     */
    @Override
    public List<Orders> querySomeOrd(Integer userId, Integer operateId) {
        String status=null;
        if (operateId==1){
            status="待支付";
        }
        if (operateId==2){
            status="待发货";
        }
        if (operateId==3){
            status="待收货";
        }
        if (operateId==4){
            status="待评价";
        }
        List<Orders> orders=ordersDao.querySomeOrd(userId,status);
        Iterator<Orders> its=orders.iterator();
        while (its.hasNext()){
            Orders it=its.next();
            List<OrdersInfo> ordersInfos=ordersInfoDao.queryByOrdersId(it.getOrderId());
            Map<String,List<OrdersInfo>> collect=ordersInfos.stream().collect(Collectors.groupingBy(OrdersInfo::getOrderId));
            List<OrdersInfo> list=collect.get(it.getOrderId());
            it.setOrdersInfos(list);
        }
        return orders;
    }

    /**
     * 通过订单中该商品的状态对该商品判断评论权限
     * @param userId
     * @param proId
     * @return
     */
    @Override
    public Boolean queryByUIdPId(Integer userId, Integer proId) {
        String status="待评价";
        Boolean comment=false;
        List<Orders> orders=ordersDao.querySomeOrd(userId,status);//查询该用户下订单状态为 待支付 状态的订单
        Iterator<Orders> its=orders.iterator();
        while (its.hasNext()){
            Orders it=its.next();
            List<OrdersInfo> ordersInfos=ordersInfoDao.queryByOIdPId(it.getOrderId(),proId);
            if (ordersInfos.size()!=0){
                comment=true;
            }
        }
        return comment;
    }


}