package com.xiaoke.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.xiaoke.springboot.entity.Comment;
import com.xiaoke.springboot.entity.Product;
import com.xiaoke.springboot.entity.User;
import com.xiaoke.springboot.service.CommentService;
import com.xiaoke.springboot.service.OrdersService;
import com.xiaoke.springboot.service.ProductService;
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

    @Resource
    private CommentService commentService;

    @Resource
    private OrdersService ordersService;

    /**
     * 通过proId查询商品详情，并查询出该商品的所有评论
     *
     * @return 单条数据
     */
    @GetMapping("details/{ProId}")
    public String details(@PathVariable("ProId") Integer ProId, HttpSession session) {
        List<Comment> comments = commentService.queryByProId(ProId);
        Product product = productService.queryById(ProId);
        User user=(User) session.getAttribute("Login");
        if (user!=null){
            Boolean can;
            can=ordersService.queryByUIdPId(user.getUserId(),ProId);
            session.setAttribute("can",can);
        }
        session.setAttribute("product", product);
        session.setAttribute("comments", comments);
        return "gooddetails";
    }

    /**
     * 查询该父类下所有的产品
     *
     * @param pid
     * @param session
     * @return
     */
    @GetMapping("list/{ParentId}")
    public String list(@PathVariable("ParentId") Integer pid, HttpSession session) {
        List<Product> products = productService.queryAllProByPid(pid);
        session.setAttribute("products", products);
        return "index";
    }

    /**
     * 模糊查询商品
     */
    @PostMapping("search")
    public String list(@RequestParam("keyWord") String keyWord,
                       HttpSession session) {
        List<Product> products = productService.queryByLike(keyWord);
        session.setAttribute("products", products);
        return "index";
    }

    /**
     * 查询子类商品
     */
    @GetMapping("children/{TypeId}")
    public String children(@PathVariable("TypeId") Integer typeId,
                           HttpSession session) {
        List<Product> products = productService.queryByTypeId(typeId);
        session.setAttribute("products", products);
        return "index";
    }

    /**
     * 跳转到商品列表页面
     *
     * @param
     * @return
     */
    @GetMapping("list")
    public String tolist(@RequestParam(defaultValue = "1") Integer pageNum, HttpSession session) {
        PageMethod.startPage(pageNum, 12);
        PageInfo pageInfo = new PageInfo(productService.queryAllPro());
        session.setAttribute("pageInfo", pageInfo);
        return "goodslist";
    }

    /**
     * 跳转到添加商品页面
     *
     * @param
     * @return
     */
    @GetMapping("add")
    public String add() {
        return "addgood";
    }

    /**
     * 添加商品到数据库
     *
     * @param product
     * @return
     */
    @PostMapping("add")
    public String add(Product product, Map<String, Object> map) {
        Product product1 = productService.queryByName(product.getProName());
        if (product1 == null) {
            product.setProDate(new Date());
            productService.insert(product);
            map.put("success", "商品添加成功!");
            return "redirect:/goods/list";
        } else {
            map.put("error", "该商品已存在!");
            return "addgood";
        }
    }

    /**
     * 根据ProId删除产品
     *
     * @return
     */
    @PostMapping("delete/{ProId}")
    public String delete(@PathVariable("ProId") Integer ProId, Map<String, Object> map) {
        productService.deleteById(ProId);
        map.put("success", "删除成功");
        return "redirect:/goods/list";
    }

    /**
     * 跳转到修改商品信息页面
     *
     * @param ProId
     * @param model
     * @return
     */
    @GetMapping("updata/{ProId}")
    public String updata(@PathVariable("ProId") Integer ProId, Model model) {
        Product product = productService.queryById(ProId);
        model.addAttribute("product", product);
        return "goodupdata";
    }

    /**
     * 修改商品信息
     *
     * @param product
     * @param map
     * @return
     */
    @PostMapping("updata")
    public String updata(Product product, Map<String, Object> map) {
        productService.update(product);
        map.put("success", "修改成功");
        return "redirect:/goods/list";
    }

    @GetMapping("page")
    public String page(@RequestParam(defaultValue = "1") Integer pageNum,
                       HttpSession session) {
        PageHelper.startPage(pageNum, 12);
        PageInfo pageInfo = new PageInfo(productService.queryAllPro());
        session.setAttribute("pageInfo", pageInfo);
        return "goodslist";
    }

}