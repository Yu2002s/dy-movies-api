package xyz.jdynb.dymovies.admin.utils;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;
import xyz.jdynb.dymovies.admin.job.base.AbstractCollectJob;
import xyz.jdynb.dymovies.admin.pojo.SchedulerJob;
import xyz.jdynb.dymovies.admin.vo.SchedulerJobVo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 调度管理器
 */
@Component
@Slf4j
public class SchedulerManager {

    @Resource
    private Scheduler scheduler;

    /**
     * 激活任务, 如果任务不存在则创建任务，存在则进行更新
     *
     * @param schedulerJob 具体任务明细
     */
    public boolean activeJob(SchedulerJob schedulerJob) {
        log.info("activeJob: {}", schedulerJob);
        JobKey jobKey = JobKey.jobKey(schedulerJob.getName(), schedulerJob.getGroup());
        try {
            if (scheduler.checkExists(jobKey)) {
                return updateJob(schedulerJob);
            } else {
                return createJob(schedulerJob);
            }
        } catch (SchedulerException e) {
            log.error("activeJob: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 创建任务并加入调度
     *
     * @param schedulerJob 具体任务信息
     */
    @SuppressWarnings("unchecked")
    public boolean createJob(SchedulerJob schedulerJob) {
        log.info("createJob: {}", schedulerJob);
        JobKey jobKey = JobKey.jobKey(schedulerJob.getName(), schedulerJob.getGroup());
        try {
            if (scheduler.checkExists(jobKey)) {
                return false;
            }
            Class<?> clazz = Class.forName(schedulerJob.getJobClass());
            JobDetail jobDetail = getJobDetail(schedulerJob, (Class<Job>) clazz);
            jobDetail.getJobDataMap().put(AbstractCollectJob.JOB_KEY_URL, schedulerJob.getUrl());
            Trigger cronTrigger = getCronTrigger(schedulerJob);
            //加入调度器
            scheduler.scheduleJob(jobDetail, cronTrigger);
            return true;
        } catch (ClassNotFoundException | SchedulerException e) {
            log.error("createJob: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 更新任务
     *
     * @param schedulerJob 具体的任务信息
     */
    @SuppressWarnings("unchecked")
    public boolean updateJob(SchedulerJob schedulerJob) {
        log.info("updateJob: {}", schedulerJob);
        TriggerKey triggerKey = TriggerKey.triggerKey(schedulerJob.getName(), schedulerJob.getGroup());
        try {
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return false;
            }
            JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(schedulerJob.getName(), schedulerJob.getGroup()));
            JobDataMap dataMap = jobDetail.getJobDataMap();
            String url = dataMap.getString(AbstractCollectJob.JOB_KEY_URL);
            if (!schedulerJob.getUrl().equals(url) || !jobDetail.getJobClass().getName().equals(schedulerJob.getJobClass())) {
                Class<?> clazz = Class.forName(schedulerJob.getJobClass());
                JobDetail newJobDetail = getJobDetail(schedulerJob, (Class<Job>) clazz);
                newJobDetail.getJobDataMap().put(AbstractCollectJob.JOB_KEY_URL, schedulerJob.getUrl());
                scheduler.addJob(newJobDetail, true);
            }
            //查询cron
            String oldCron = ((CronTrigger) trigger).getCronExpression();
            //没有变化则返回
            if (oldCron.equals(schedulerJob.getCron())) {
                return true;
            }
            Trigger cronTrigger = getCronTrigger(schedulerJob);
            // 重新调度任务
            scheduler.rescheduleJob(triggerKey, cronTrigger);
            return true;
        } catch (SchedulerException | ClassNotFoundException e) {
            log.error("updateJob: {}", e.getMessage());
            return false;
        }
    }

    public boolean deleteJobs(List<SchedulerJob> jobs) {
        return deleteJobsForKey(jobs.stream().map(job -> JobKey.jobKey(job.getName(), job.getGroup())).toList());
    }

    /**
     * 通过 key 列表删除任务
     *
     * @param jobKeys 唯一标识列表
     */
    public boolean deleteJobsForKey(List<JobKey> jobKeys) {
        log.info("deleteJobs: {}", jobKeys);
        try {
            return scheduler.deleteJobs(jobKeys);
        } catch (SchedulerException e) {
            log.error("deleteJobs: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 通过 key 删除任务
     *
     * @param jobKey 唯一标识
     * @return 是否成功
     */
    public boolean deleteJob(JobKey jobKey) {
        log.info("deleteJob: {}", jobKey);
        try {
            return scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.error("deleteJob: {}", e.getMessage());
            return false;
        }
    }

    public boolean pauseJob(SchedulerJob job) {
        return pauseJob(JobKey.jobKey(job.getName(), job.getGroup()));
    }

    public boolean pauseJob(JobKey jobKey) {
        log.info("pauseJob: {}", jobKey);
        try {
            scheduler.pauseJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            log.error("pauseJob: {}", e.getMessage());
            return false;
        }
    }

    public boolean pauseJobs(String group) {
        try {
            scheduler.pauseJobs(GroupMatcher.jobGroupEquals(group));
            return true;
        } catch (SchedulerException e) {
            return false;
        }
    }

    public boolean resumeJob(SchedulerJob job) {
        return resumeJob(JobKey.jobKey(job.getName(), job.getGroup()));
    }

    public boolean resumeJob(JobKey jobKey) {
        log.info("resumeJob: {}", jobKey);
        try {
            scheduler.resumeJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            log.error("resumeJob: {}", e.getMessage());
            return false;
        }
    }

    public boolean resumeAll() {
        log.info("resumeAll");
        try {
            scheduler.resumeAll();
            return true;
        } catch (SchedulerException e) {
            log.error("resumeAll: {}", e.getMessage());
            return false;
        }
    }

    public boolean pauseAll() {
        log.info("pauseAll");
        try {
            scheduler.pauseAll();
            return true;
        } catch (SchedulerException e) {
            log.error("pauseAll: {}", e.getMessage());
            return false;
        }
    }

    public List<SchedulerJobVo> getJobs() {
        log.info("getJobs");
        try {
            return scheduler.getJobKeys(GroupMatcher.anyGroup()).stream().map(jobKey -> {
                try {
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                    TriggerKey triggerKey = TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup());
                    // 没有对 trigger 进行判断
                    Trigger trigger = scheduler.getTrigger(triggerKey);
                    return createSchedulerJob(jobDetail, trigger);
                } catch (Exception error) {
                    log.error("getJobs: {}", error.getMessage());
                }
                return null;
            }).toList();
        } catch (SchedulerException e) {
            log.error("getJobs: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<SchedulerJobVo> getJobsTree() {
        List<SchedulerJobVo> jobs = getJobs();

        // 根据 group 字段进行分组
        Map<String, List<SchedulerJobVo>> groupedJobs = jobs.stream()
                .collect(Collectors.groupingBy(SchedulerJobVo::getGroup));

        // 将分组后的结果转换为 List
        return groupedJobs.entrySet().stream()
                .map(entry -> {
                    SchedulerJobVo job = new SchedulerJobVo();
                    job.setName(entry.getKey());
                    job.setChildren(entry.getValue());
                    return job;
                }).toList();
    }

    public int getTriggerState(TriggerKey triggerKey) {
        try {
            Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            return triggerState.ordinal();
        } catch (SchedulerException e) {
            log.error("getTriggerState: {}", e.getMessage());
        }
        return -1;
    }

    /**
     * 通过 jobDetail 和 Trigger 创建对应的 SchedulerJob
     *
     * @param jobDetail 任务信息
     * @param trigger   触发器
     * @return SchedulerJob 对象
     */
    private SchedulerJobVo createSchedulerJob(JobDetail jobDetail, Trigger trigger) {
        SchedulerJobVo schedulerJob = new SchedulerJobVo();
        JobKey key = jobDetail.getKey();
        schedulerJob.setName(key.getName());
        schedulerJob.setGroup(key.getGroup());
        // trigger 其实有多种类型
        if (trigger instanceof CronTrigger) {
            schedulerJob.setCron(((CronTrigger) trigger).getCronExpression());
            schedulerJob.setStatus(getTriggerState(trigger.getKey()));
        }
        schedulerJob.setJobClass(jobDetail.getJobClass().getName());
        schedulerJob.setDesc(jobDetail.getDescription());
        schedulerJob.setUrl(jobDetail.getJobDataMap().getString(AbstractCollectJob.JOB_KEY_URL));
        // schedulerJob.setArgs(trigger.getJobDataMap());
        return schedulerJob;
    }

    /**
     * 创建任务
     *
     * @param schedulerJob 具体任务信息
     * @param clazz        对应任务类信息
     * @return 工作信息
     */
    private JobDetail getJobDetail(SchedulerJob schedulerJob, Class<Job> clazz) {
        return JobBuilder.newJob()
                .ofType(clazz)
                .withIdentity(schedulerJob.getName(), schedulerJob.getGroup())
                .withDescription(schedulerJob.getDesc())
                .storeDurably()
                .build();
    }

    /**
     * 创建触发器
     *
     * @param schedulerJob 具体任务信息
     * @return 触发器信息
     */
    private Trigger getCronTrigger(SchedulerJob schedulerJob) {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob.getCron());
        ;
        return TriggerBuilder.newTrigger()
                .withIdentity(schedulerJob.getName(), schedulerJob.getGroup())
                .withDescription(schedulerJob.getDesc())
                .withSchedule(cronScheduleBuilder)
                .build();
    }

}