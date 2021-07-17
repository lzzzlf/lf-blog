package com.lzzz.blog.service;

import com.lzzz.blog.dto.VisitLogUuidTimeDTO;
import com.lzzz.blog.entity.VisitLog;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 15:18
 */
public interface VisitLogService {

    @Async
    void saveVisitLog(VisitLog log);

    void deleteVisitLogById(Long id);

    List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

    List<VisitLogUuidTimeDTO> getUUIDAndCreateTimeByYesterday();



}
