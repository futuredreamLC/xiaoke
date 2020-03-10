package com.xiaoke.springboot.controller;

import com.xiaoke.springboot.entity.Product;
import com.xiaoke.springboot.service.ProductService;
import com.xiaoke.springboot.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * (Product)表控制层
 *
 * @author makejava
 * @since 2020-03-05 16:49:47
 */
@Controller
@RequestMapping("goods")
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;

    @Resource
    private TypeService typeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Product selectOne(Integer id) {
        return this.productService.queryById(id);
    }

    @GetMapping("list/{ParentId}")
    public String list(@PathVariable("ParentId") Integer pid,HttpSession session){
        List<Product> products=productService.queryAllProByPid(pid);
        session.setAttribute("products",products);
        return null;
    }
    /**
     * 跳转到商品列表页面
     * @param
     * @return
     */
    @GetMapping("list")
    public String list(HttpSession session){
        List<Product> products=productService.queryAllPro();
        session.setAttribute("goods",products);
        return "goodslist";
    }

    /**
     * 跳转到添加商品页面
     * @param
     * @return
     */
    @GetMapping("add")
    public String add(){
        return "addgood";
    }

    /**
     * 添加商品到数据库
     * @param product
     * @return
     */
    @PostMapping("add")
    public String add(Product product, Map<String,Object> map){
        Product product1=productService.queryByName(product.getProName());
        if(product1==null) {
            product.setProDate(new Date());
            productService.insert(product);
            map.put("success","商品添加成功!");
            return "goodslist";
        }else {
            map.put("error","该商品已存在!");
            return "addgood";
        }
    }

}