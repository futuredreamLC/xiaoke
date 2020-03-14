package com.xiaoke.springboot.controller;

import com.xiaoke.springboot.entity.Product;
import com.xiaoke.springboot.service.ProductService;
import com.xiaoke.springboot.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    /**
     * 查询该父类下所有的产品
     * @param pid
     * @param session
     * @return
     */
    @GetMapping("list/{ParentId}")
    public String list(@PathVariable("ParentId") Integer pid,HttpSession session){
        List<Product> products=productService.queryAllProByPid(pid);
        session.setAttribute("products",products);
        return "index";
    }
    /**
     * 模糊查询商品
     */
    @PostMapping("search")
    public String list(@RequestParam("keyWord") String keyWord,HttpSession session){
        session.setAttribute("products",productService.queryByLike(keyWord));
        return "index";
    }
    /**
     * 查询子类商品
     */
    @GetMapping("children/{TypeId}")
    public String children(@PathVariable("TypeId") Integer typeId,HttpSession session){
        session.setAttribute("products",productService.queryByTypeId(typeId));
        return "index";
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
            return "redirect:/goods/list";
        }else {
            map.put("error","该商品已存在!");
            return "addgood";
        }
    }

    /**
     * 根据ProId删除产品
     * @return
     */
    @PostMapping("delete/{ProId}")
    public String delete(@PathVariable("ProId") Integer ProId,Map<String,Object> map){
        productService.deleteById(ProId);
        map.put("success","删除成功");
        return "goodslist";
    }

    /**
     * 跳转到修改商品信息页面
     * @param ProId
     * @param model
     * @return
     */
    @GetMapping("updata/{ProId}")
    public String updata(@PathVariable("ProId") Integer ProId, Model model){
        Product product=productService.queryById(ProId);
        model.addAttribute("product",product);
        return "goodupdata";
    }

    /**
     * 修改商品信息
     * @param product
     * @param map
     * @return
     */
    @PostMapping("updata")
    public String updata(Product product,Map<String,Object> map){
            productService.update(product);
            map.put("success","修改成功");
            return "redirect:/goods/list";
    }

}