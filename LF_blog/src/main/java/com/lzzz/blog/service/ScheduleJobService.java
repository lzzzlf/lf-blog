package com.lzzz.blog.service;

import com.lzzz.blog.entity.ScheduleJob;
import com.lzzz.blog.entity.ScheduleJobLog;

import java.util.List;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 20:15
 */
public interface ScheduleJobService {

    /**
    * 保存任务
    * @param scheduleJob
    * @return
    * @author Mashiro
    * @date 2021/5/30 20:27
    */
    void saveJob(ScheduleJob scheduleJob);

    /**
    * 保存任务日志
    * @param log
    * @return
    * @author Mashiro
    * @date 2021/5/30 20:28
    */
    void saveJobLog(ScheduleJobLog log);

    /**
    * 通过id删除任务
    * @param jobId
    * @return
    * @author Mashiro
    * @date 2021/5/30 20:28
    */
    void deleteJobById(Long jobId);

    /**
    * 通过id删除任务日志
    * @param logId
    * @return
    * @author Mashiro
    * @date 2021/5/30 20:28
    */
    void deleteJobLogByLogId(Long logId);

    /**
    * 更新任务
    * @param scheduleJob
    * @return
    * @author Mashiro
    * @date 2021/5/30 20:28
    */
    void updateJob(ScheduleJob scheduleJob);

    /**
    * 更新任务状态
    * @param jobId
    * @param status
    * @return
    * @author Mashiro
    * @date 2021/5/30 20:28
    */
    void updateJobStatusById(Long jobId, Boolean status);

    /**
    * 运行任务
    * @param jobId
    * @return
    * @author Mashiro
    * @date 2021/5/30 20:29
    */
    void runJobById(Long jobId);

    /**
    * 任务列表
    * @param
    * @return {@link List< ScheduleJob>}
    * @author Mashiro
    * @date 2021/5/30 20:29
    */
    List<ScheduleJob> getJobList();

    /**
    * 任务日志列表
    * @param startDate
    * @param endDate
    * @return {@link List<com.lzzz.blog.entity.ScheduleJobLog>}
    * @author Mashiro
    * @date 2021/5/30 20:29
    */
    List<ScheduleJobLog> getJobLogListByDate(String startDate, String endDate);

}
