package com.xiaoke.springboot.service.impl;

import com.xiaoke.springboot.entity.Shoppingcart;
import com.xiaoke.springboot.dao.ShoppingcartDao;
import com.xiaoke.springboot.service.ShoppingcartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     * @param limit 查询条数
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
        this.shoppingcartDao.insert(shoppingcart);
        return shoppingcart;
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
}