package com.lzzz.blog.controller;

import com.lzzz.blog.common.Result;
import com.lzzz.blog.entity.Category;
import com.lzzz.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 类型管理控制器
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 15:45
 */
@Api(tags = "分类模块")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "查看所有分类")
    @GetMapping("/categories")
    public Result listCategories() {
        List<Category> categoryList = categoryService.getCategoryList();
        int count = categoryList.size();
        Map<String, Object> map = new HashMap<>();
        map.put("categoryList", categoryList);
        map.put("count", count);
        return Result.success(map);
    }
}
