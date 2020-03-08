package com.xiaoke.springboot.controller;

import com.xiaoke.springboot.entity.Product;
import com.xiaoke.springboot.service.ProductService;
import com.xiaoke.springboot.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class indexController {

    @Resource
    ProductService productService;

    @Resource
    TypeService typeService;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("types",typeService.queryTypeByPid(-1));

        return "index";
    }
}
