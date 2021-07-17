package com.lzzz.blog.service.Impl;

import com.lzzz.blog.common.RedisKey;
import com.lzzz.blog.dto.VisitLogUuidTimeDTO;
import com.lzzz.blog.entity.Visitor;
import com.lzzz.blog.exception.PersistenceException;
import com.lzzz.blog.mapper.VisitorMapper;
import com.lzzz.blog.service.RedisService;
import com.lzzz.blog.service.VisitorService;
import com.lzzz.blog.util.IpAddressUtils;
import com.lzzz.blog.util.UserAgentUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 访客统计业务层实现
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 15:59
 */
@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorMapper visitorMapper;
    private final RedisService redisService;
    private final UserAgentUtils userAgentUtils;

    public VisitorServiceImpl(VisitorMapper visitorMapper, RedisService redisService, UserAgentUtils userAgentUtils) {
        this.visitorMapper = visitorMapper;
        this.redisService = redisService;
        this.userAgentUtils = userAgentUtils;
    }

    @Override
    public void saveVisitor(Visitor visitor) {
        String ipSource = IpAddressUtils.getCityInfo(visitor.getIp());
        Map<String, String> userAgentMap = userAgentUtils.parseOsAndBrowser(visitor.getUserAgent());
        String os = userAgentMap.get("os");
        String browser = userAgentMap.get("browser");
        visitor.setIpSource(ipSource);
        visitor.setOs(os);
        visitor.setBrowser(browser);
        if (visitorMapper.saveVisitor(visitor) != 1) {
            throw new PersistenceException("访客添加失败");
        }
    }

    @Override
    public void deleteVisitor(Long id, String uuid) {
        redisService.deleteValueBySet(RedisKey.IDENTIFICATION_SET, uuid);
        if (visitorMapper.deleteVisitorById(id) != 1){
            throw new PersistenceException("删除访客失败");
        }
    }

    @Override
    public void updatePVAndLastTimeByUUID(VisitLogUuidTimeDTO dto) {
        visitorMapper.updatePVAndLastTimeByUUID(dto);
    }

    @Override
    public boolean hasUUID(String uuid) {
        return visitorMapper.hasUUID(uuid) != 0;
    }

    @Override
    public List<Visitor> getVisitorListByDate(String startDate, String endDate) {
        return visitorMapper.getVisitorListByDate(startDate, endDate);
    }

    @Override
    public List<String> getNewVisitorIpSourceByYesterday() {
        return visitorMapper.getNewVisitorIpSourceByYesterday();
    }
}
