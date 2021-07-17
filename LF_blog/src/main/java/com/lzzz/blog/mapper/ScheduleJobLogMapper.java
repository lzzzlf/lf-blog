package com.lzzz.blog.mapper;

import com.lzzz.blog.entity.ScheduleJobLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/27 21:43
 */
@Mapper
@Repository
public interface ScheduleJobLogMapper {

    int saveJobLog(ScheduleJobLog scheduleJoblog);

    int deleteJobLogByLogId(Long LogId);

    List<ScheduleJobLog> getJobLogListByDate(String startDate, String endDate);
}
