package com.lzzz.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzzz.blog.annotation.OperationLogger;
import com.lzzz.blog.common.Result;
import com.lzzz.blog.entity.Category;
import com.lzzz.blog.service.BlogService;
import com.lzzz.blog.service.CategoryService;
import com.lzzz.blog.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/29 17:52
 */
@Api(tags = "后台分类管理模块")
@RestController
@RequestMapping("/admin")
public class CategoryAdminController {

    private final BlogService blogService;
    private final CategoryService categoryService;

    public CategoryAdminController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    /**
    * 添加分类
    * @param category 分类对象
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 18:17
    */
    @ApiOperation(value = "添加分类")
    @OperationLogger("添加分类")
    @RequestMapping("/category")
    public Result saveCategory(@RequestBody Category category){
        return saveAndUpdateCheckDate(category, "save");
    }

    /**
    * 删除评论
    * @param id 评论 id
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 18:24
    */
    @ApiOperation(value = "删除评论")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "Long", paramType = "query")
    @OperationLogger("删除评论")
    @DeleteMapping("/category")
    public Result deleteCategory(@RequestParam Long id){
        //删除存在博客关联的分类后，该博客的查询会出异常
        int num = blogService.countBlogByCategoryId(id);
        if (num != 0) {
            return Result.error("已有博客与此分类关联，不可删除");
        }
        categoryService.deleteCategoryById(id);
        return Result.success("删除评论成功");
    }

    /**
    * 修改分类
    * @param category 分类对象
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 18:26
    */
    @ApiOperation(value = "修改分类")
    @OperationLogger("修改分类")
    @PutMapping("/category")
    public Result updateCategory(@RequestBody Category category) {
        return saveAndUpdateCheckDate(category, "update");
    }

    /**
    * 分类列表
    * @param pageNum 页码
    * @param pageSize 每页个数
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 18:29
    */
    @ApiOperation(value = "分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, defaultValue = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数目", required = true, defaultValue = "10", dataType = "Integer", paramType = "query")
    })
    @GetMapping("/categories")
    public Result categoryList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryService.getCategoryList());
        return Result.success(pageInfo);
    }

    public Result saveAndUpdateCheckDate(@RequestBody Category category, @RequestParam String type){
        if (StringUtils.isEmpty(category.getName())){
            return Result.error("分类名称不能为空");
        }
        Category c = categoryService.getCategoryByName(category.getName());
        if (c != null && !c.getId().equals(category.getId())){
            return Result.error("该分类已存在");
        }
        if ("save".equals(type)){
            categoryService.saveCategory(category);
            return Result.success("新增分类成功");
        }else {
            categoryService.updateCategory(category);
            return Result.success("分类更新成功");
        }
    }

}
