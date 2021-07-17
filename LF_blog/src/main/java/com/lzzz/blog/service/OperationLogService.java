package com.lzzz.blog.service;

import com.lzzz.blog.entity.OperationLog;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:45
 */
public interface OperationLogService {

    @Async
    void saveOperationLog(OperationLog log);

    void deleteOperationLogById(Long id);

    List<OperationLog> getOperationLogListByDate(String startDate, String endDate);



}
