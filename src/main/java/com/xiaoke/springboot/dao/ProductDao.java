package com.xiaoke.springboot.dao;

import com.xiaoke.springboot.entity.Product;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Product)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-05 16:49:44
 */
public interface ProductDao {

    /**
     * 通过ID查询单条数据
     *
     * @param proId 主键
     * @return 实例对象
     */
    Product queryById(Integer proId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Product> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param product 实例对象
     * @return 对象列表
     */
    List<Product> queryAll(Product product);

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 影响行数
     */
    int insert(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 影响行数
     */
    int update(Product product);

    /**
     * 通过主键删除数据
     *
     * @param proId 主键
     * @return 影响行数
     */
    int deleteById(Integer proId);

    /**
     * 通过商品名查询商品
     * @param proName
     * @return
     */
    Product queryByName(String proName);

    /**
     * 查询所有的商品并附上商品对应的类别信息
     * @return
     */
    List<Product> queryAllPro();

    /**
     * 通过父类查询该父类下的所有产品
     */
    List<Product> queryAllProByPid(Integer pid);
    /**
     * 通过子类别查询所有产品
     */
    List<Product> queryByTypeId(Integer typeId);
    /**
     * 模糊查询
     */
    List<Product> queryByLike(String keyWord);
}