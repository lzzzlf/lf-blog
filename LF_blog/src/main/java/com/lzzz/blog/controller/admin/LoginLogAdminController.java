package com.lzzz.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzzz.blog.annotation.OperationLogger;
import com.lzzz.blog.common.Result;
import com.lzzz.blog.entity.LoginLog;
import com.lzzz.blog.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 后台登陆日志管理模块
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:54
 */
@Api(tags = "后台登陆日志管理模块")
@RestController
@RequestMapping("/admin")
public class LoginLogAdminController {

    private final LoginLogService loginLogService;

    public LoginLogAdminController(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    /**
     * 分页查询登录日志列表
     *
     * @param date     按操作时间查询
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return {@link Result}
     * @author Mashiro
     * @date 2021/5/30 15:00
     */
    @ApiOperation(value = "分页查询登录日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "操作时间", dataType = "String[]", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, defaultValue = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数目", required = true, defaultValue = "10", dataType = "Integer", paramType = "query")

    })
    @GetMapping("/loginLogs")
    public Result loginLogs(@RequestParam(defaultValue = "") String[] date,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
        String startDate = null;
        String endDate = null;
        if (date.length == 2) {
            startDate = date[0];
            endDate = date[1];
        }
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<LoginLog> pageInfo = new PageInfo<>(loginLogService.getLoginLogByDate(startDate, endDate));
        return Result.success(pageInfo);
    }

    /**
     * 按id删除登录日志
     *
     * @param id 日志id
     * @return {@link Result}
     * @author Mashiro
     * @date 2021/5/30 15:00
     */
    @ApiOperation(value = "删除登录日志")
    @ApiImplicitParam(name = "id", value = "登录日志Id", required = true, dataType = "Long", paramType = "query")
    @OperationLogger(value = "删除登录日志")
    @DeleteMapping("/loginLog")
    public Result delete(@RequestParam Long id) {
        loginLogService.deleteLoginLog(id);
        return Result.success("删除成功");
    }
}
