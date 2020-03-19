package com.xiaoke.springboot.controller;

import com.xiaoke.springboot.entity.Comment;
import com.xiaoke.springboot.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Comment)表控制层
 *
 * @author makejava
 * @since 2020-03-02 11:10:09
 */
@Controller
@RequestMapping("comment")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Comment selectOne(Integer id) {
        return this.commentService.queryById(id);
    }

}