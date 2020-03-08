package com.xiaoke.springboot.controller;

import com.xiaoke.springboot.entity.Product;
import com.xiaoke.springboot.service.ProductService;
import com.xiaoke.springboot.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @GetMapping("list")
    public String list(Model model){
        model.addAttribute("types",typeService.queryTypeByPid(-1));
        return "goodslist";
    }
    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("types",typeService.queryTypeByPid(-1));
        return "addgood";
    }
    @PostMapping("add")
    public String add(Product product){
        System.out.println(product.getProImage());
        System.out.println(product.getTypeId());
        return "goodslist";
    }

}