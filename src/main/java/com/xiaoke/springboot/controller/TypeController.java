package com.xiaoke.springboot.controller;

import com.xiaoke.springboot.entity.Type;
import com.xiaoke.springboot.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (Type)表控制层
 *
 * @author makejava
 * @since 2020-03-05 16:50:32
 */
@Controller
@RequestMapping("type")
public class TypeController {
    /**
     * 服务对象
     */
    @Resource
    private TypeService typeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Type selectOne(Integer id) {
        return this.typeService.queryById(id);
    }

    @GetMapping("add")
    public String Typeadd(Model model){
        model.addAttribute("types",typeService.queryTypeByPid(-1));
        return "typeadd";
    }


    @PostMapping("add")
    public String Typeadd(Type type, Map<String,Object> map) {
            Type type1 = typeService.queryTypeByName(type.getTypename());
            if (type1 == null) {
                typeService.insert(type);
                map.put("success", "类别添加成功！");
                return "typeadd";
            } else {
                map.put("error", "该类别已经存在！");
                return "typeadd";
            }
        }
}