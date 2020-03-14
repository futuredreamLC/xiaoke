package com.xiaoke.springboot.service.impl;

import com.xiaoke.springboot.dao.TypeDao;
import com.xiaoke.springboot.entity.Product;
import com.xiaoke.springboot.dao.ProductDao;
import com.xiaoke.springboot.entity.Type;
import com.xiaoke.springboot.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (Product)表服务实现类
 *
 * @author makejava
 * @since 2020-03-05 16:49:46
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;

    @Resource
    private TypeDao typeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param proId 主键
     * @return 实例对象
     */
    @Override
    public Product queryById(Integer proId) {
        Product product=productDao.queryById(proId);
        Type type=typeDao.queryById(product.getType().getParentId());
        product.setParentType(type);
        return product;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Product> queryAllByLimit(int offset, int limit) {
        return this.productDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product insert(Product product) {
        this.productDao.insert(product);
        return product;
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product update(Product product) {
        this.productDao.update(product);
        return this.queryById(product.getProId());
    }

    /**
     * 通过主键删除数据
     *
     * @param proId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer proId) {
        return this.productDao.deleteById(proId) > 0;
    }

    /**
     * 通过商品名查询商品
     * @param proName
     * @return
     */
    @Override
    public Product queryByName(String proName) {
        return this.productDao.queryByName(proName);
    }

    /**
     * 查询所有的商品
     * @return
     */
    @Override
    public List<Product> queryAllPro() {
        List<Product> products=productDao.queryAllPro();
        Iterator<Product> its=products.iterator();
        while (its.hasNext()){
            Product it=its.next();
            Type ptype=typeDao.queryById(it.getType().getParentId());
            it.setParentType(ptype);
        }
        return products;
    }

    /**
     * 通过父类id查询该类下的所有产品
     * @param Pid
     * @return
     */
    @Override
    public List<Product> queryAllProByPid(Integer Pid) {
        return this.productDao.queryAllProByPid(Pid);

    }

    /**
     * 通过子类别查询所有产品
     * @param typeId
     * @return
     */
    @Override
    public List<Product> queryByTypeId(Integer typeId) {
        return this.productDao.queryByTypeId(typeId);
    }

    /**
     * 模糊查询
     * @param
     * @return
     */
    @Override
    public List<Product> queryByLike(String keyWord) {
        return this.productDao.queryByLike(keyWord);

    }
}