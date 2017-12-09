package com.johnny.cms.controller;

import com.johnny.cms.bean.Msg;
import com.johnny.cms.domain.Category;
import com.johnny.cms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 返回所有的类别信息
     */
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts() {
        List<Category> list = categoryService.getDepts();
        return Msg.success().add("depts", list);
    }
}
