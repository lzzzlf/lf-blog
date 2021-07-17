package com.lzzz.blog.controller;

import com.lzzz.blog.annotation.VisitLogger;
import com.lzzz.blog.common.Result;
import com.lzzz.blog.service.BlogService;
import com.lzzz.blog.vo.BlogInfoVO;
import com.lzzz.blog.vo.IndexInfoVO;
import com.lzzz.blog.vo.PageResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: mashiro
 * @Date: Created in 2021/5/31 18:26
 */
@Api(tags = "首页模块")
@RestController
public class IndexController {
    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "首页网站部分摘要信息")
    @GetMapping("/")
    public Result IndexInfo(){
        //网站信息
        IndexInfoVO indexInfo = blogService.getIndexInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("indexInfo", indexInfo);
        return Result.success(map);
    }

    @ApiOperation(value = "首页博客列表")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, defaultValue = "1", dataType = "Integer", paramType = "query")
    @VisitLogger(behavior = "访问页面", content = "首页")
    @GetMapping("/indexBlogList")
    public Result Index(@RequestParam(defaultValue = "1") Integer pageNum){
        //首页博客列表
        PageResultVO<BlogInfoVO> blogList = blogService.getBlogInfoListByIsPublished(pageNum);
        Map<String, Object> map = new HashMap<>();
        map.put("blogList", blogList);
        return Result.success(map);
    }


}
