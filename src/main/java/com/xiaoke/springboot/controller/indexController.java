package com.xiaoke.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoke.springboot.entity.Product;
import com.xiaoke.springboot.service.ProductService;
import com.xiaoke.springboot.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class indexController {

    @Resource
    ProductService productService;

    @Resource
    TypeService typeService;
    @GetMapping("/")
    public String index(HttpSession session){
        List<Product> products=productService.queryAllPro();
        session.setAttribute("products",products);
        session.setAttribute("types",typeService.queryTypeByPid(-1));
        return "index";
    }
}
