package com.lzzz.blog.controller;

import com.lzzz.blog.annotation.VisitLogger;
import com.lzzz.blog.common.Result;
import com.lzzz.blog.service.BlogService;
import com.lzzz.blog.vo.ArchiveInfoVO;
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
 * @Author: Mashiro
 * @Date: Created in 2021/6/3 19:42
 */
@Api(tags = "归档模块")
@RestController
public class ArchiveController {

    @Autowired
    BlogService blogService;

    @ApiOperation(value = "归档页面")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = true, defaultValue = "1", dataType = "Integer", paramType = "query")
    @VisitLogger(behavior = "访问界面", content = "归档")
    @GetMapping("/archives")
    public Result archives(@RequestParam(defaultValue = "1") Integer pageNum) {
        int i = blogService.countBlogByIsPublished();
        PageResultVO<ArchiveInfoVO> archiveBlog = blogService.getArchiveBlog(pageNum);
        Map<String, Object> map = new HashMap<>();
        map.put("count", i);
        map.put("archiveList", archiveBlog);
        return Result.success(map);
    }
}
