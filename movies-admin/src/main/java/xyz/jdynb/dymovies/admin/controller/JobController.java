package xyz.jdynb.dymovies.admin.controller;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.admin.entity.JobClass;
import xyz.jdynb.dymovies.admin.pojo.SchedulerJob;
import xyz.jdynb.dymovies.admin.service.JobClassService;
import xyz.jdynb.dymovies.admin.utils.SchedulerManager;
import xyz.jdynb.dymovies.admin.validator.UpdateJobStatusGroup;
import xyz.jdynb.dymovies.admin.vo.SchedulerJobVo;
import xyz.jdynb.dymovies.common.pojo.Result;

import java.util.List;

@RestController
@RequestMapping("/admin/jobs")
public class JobController {

    @Resource
    private SchedulerManager schedulerManager;

    @Resource
    private JobClassService jobClassService;

    @GetMapping
    public Result<List<SchedulerJobVo>> getJobs() {
        return Result.success(schedulerManager.getJobsTree());
    }

    @PostMapping
    public Result<Void> addOrUpdateJob(@Validated @RequestBody SchedulerJob schedulerJob) {
        return schedulerManager.activeJob(schedulerJob)
                ? Result.success("操作成功")
                : Result.error("操作失败");
    }

    @DeleteMapping
    public Result<Void> deleteJob(@Validated(UpdateJobStatusGroup.class) @RequestBody List<SchedulerJob> jobs) {
        return schedulerManager.deleteJobs(jobs)
                ? Result.success("删除任务成功")
                : Result.error("删除任务失败");
    }

    @PutMapping("/pause")
    public Result<Void> pauseJob(@Validated(UpdateJobStatusGroup.class) @RequestBody SchedulerJob job) {
        return schedulerManager.pauseJob(job)
                ? Result.success("暂停任务成功")
                : Result.error("暂停任务失败");
    }

    @PutMapping("/resume")
    public Result<Void> resumeJob(@Validated(UpdateJobStatusGroup.class) @RequestBody SchedulerJob job) {
        return schedulerManager.resumeJob(job)
                ? Result.success("恢复任务成功")
                : Result.error("恢复任务失败");
    }

    @PostMapping("/resumeAll")
    public Result<Void> resumeAll() {
        return schedulerManager.resumeAll()
                ? Result.success("恢复所有任务成功")
                : Result.error("恢复所有任务失败");
    }

    @PostMapping("/pauseAll")
    public Result<Void> pauseAll() {
        return schedulerManager.pauseAll()
                ? Result.success("暂停所有任务成功")
                : Result.error("暂停所有任务失败");
    }

    @GetMapping("/class")
    public Result<List<JobClass>> getJobClass() {
        return Result.success(jobClassService.findAll());
    }

    @PostMapping("/class")
    public Result<String> addOrJobClass(@Validated @RequestBody JobClass jobClass) {
        if (jobClass.getId() == null) {
            jobClassService.add(jobClass);
        } else {
            jobClassService.update(jobClass);
        }
        return Result.success();
    }

    @DeleteMapping("/class/{id}")
    public Result<String> deleteJobClass(@PathVariable long id) {
        jobClassService.delete(id);
        return Result.success();
    }
}
