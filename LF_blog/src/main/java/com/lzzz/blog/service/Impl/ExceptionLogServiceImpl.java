package com.lzzz.blog.service.Impl;

import com.lzzz.blog.entity.ExceptionLog;
import com.lzzz.blog.exception.PersistenceException;
import com.lzzz.blog.mapper.ExceptionLogMapper;
import com.lzzz.blog.service.ExceptionLogService;
import com.lzzz.blog.util.IpAddressUtils;
import com.lzzz.blog.util.UserAgentUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 异常日志业务层实现
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:26
 */
@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

    private final ExceptionLogMapper exceptionLogMapper;
    private final UserAgentUtils userAgentUtils;

    public ExceptionLogServiceImpl(ExceptionLogMapper exceptionLogMapper, UserAgentUtils userAgentUtils) {
        this.exceptionLogMapper = exceptionLogMapper;
        this.userAgentUtils = userAgentUtils;
    }

    @Override
    public void saveExceptionLog(ExceptionLog log) {
        //获取当前操作的ip
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        //从User-Agent解析客户端操作系统和浏览器版本
        Map<String, String> userAgentMap = userAgentUtils.parseOsAndBrowser(log.getUserAgent());
        String os = userAgentMap.get("os");
        String browser = userAgentMap.get("browser");
        log.setIpSource(ipSource);
        log.setOs(os);
        log.setBrowser(browser);
        if (exceptionLogMapper.saveExceptionLog(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }

    @Override
    public void deleteExceptionLogById(Long id) {
        if (exceptionLogMapper.deleteExceptionLogById(id) != 1) {
            throw new PersistenceException("删除日志失败");
        }
    }

    @Override
    public List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate) {
        return exceptionLogMapper.getExceptionLogListByDate(startDate, endDate);
    }
}
