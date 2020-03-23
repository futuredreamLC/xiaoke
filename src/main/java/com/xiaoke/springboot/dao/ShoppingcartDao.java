package com.xiaoke.springboot.dao;

import com.xiaoke.springboot.entity.Shoppingcart;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Shoppingcart)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-02 12:39:47
 */
public interface ShoppingcartDao {

    /**
     * 通过ID查询单条数据
     *
     * @param cartId 主键
     * @return 实例对象
     */
    Shoppingcart queryById(Integer cartId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Shoppingcart> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param shoppingcart 实例对象
     * @return 对象列表
     */
    List<Shoppingcart> queryAll(Shoppingcart shoppingcart);

    /**
     * 新增数据
     *
     * @param shoppingcart 实例对象
     * @return 影响行数
     */
    int insert(Shoppingcart shoppingcart);

    /**
     * 修改数据
     *
     * @param shoppingcart 实例对象
     * @return 影响行数
     */
    int update(Shoppingcart shoppingcart);

    /**
     * 通过主键删除数据
     *
     * @param cartId 主键
     * @return 影响行数
     */
    int deleteById(Integer cartId);

    /**
     * 通过用户id查询该用户购物车中的所有产品
     * @param userId
     * @return
     */
    List<Shoppingcart> queryByUid(Integer userId);

    /**
     * 通过产品id查询购物车中是否有此产品
     * @param proId
     * @return
     */
    Shoppingcart queryByProId(Integer proId);
}