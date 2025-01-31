package xyz.jdynb.dymovies.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.jdynb.dymovies.common.pojo.Result;
import xyz.jdynb.dymovies.config.SchedulerManager;
import xyz.jdynb.dymovies.vo.SchedulerJobVo;
import xyz.jdynb.dymovies.entity.JobClass;
import xyz.jdynb.dymovies.pojo.SchedulerJob;
import xyz.jdynb.dymovies.service.JobClassService;
import xyz.jdynb.dymovies.service.VodProviderService;
import xyz.jdynb.dymovies.validator.UpdateJobStatusGroup;

import java.util.List;

@RestController
@RequestMapping("/admin/jobs")
public class JobController {

    @Resource
    private SchedulerManager schedulerManager;

    @Resource
    private JobClassService jobClassService;

    @Resource
    private VodProviderService vodProviderService;

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
}
