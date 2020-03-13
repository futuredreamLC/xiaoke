package com.xiaoke.springboot.service;

import com.xiaoke.springboot.entity.Product;
import java.util.List;

/**
 * (Product)表服务接口
 *
 * @author makejava
 * @since 2020-03-05 16:49:45
 */
public interface ProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param proId 主键
     * @return 实例对象
     */
    Product queryById(Integer proId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Product> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product insert(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product update(Product product);

    /**
     * 通过主键删除数据
     *
     * @param proId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer proId);

    /**
     * 通过商品名查询商品
     * @param proName
     * @return
     */
    Product queryByName(String proName);

    /**
     * 查询所有的产品
     * @return
     */
    List<Product> queryAllPro();

    /**
     * 通过父类查询该父类下所有的产品
     */
    List<Product> queryAllProByPid(Integer Pid);
    /**
     * 通过子类别查询所有产品
     */
    List<Product> queryByTypeId(Integer typeId);
    /**
     * 模糊查询
     */
    List<Product> queryByLike(String keyWord);
}