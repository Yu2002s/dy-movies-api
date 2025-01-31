package xyz.jdynb.dymovies.init;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import xyz.jdynb.dymovies.config.SchedulerManager;

/**
 * 程序初始化时进行一些操作
 */
@Component
@Slf4j
public class Initialization implements ApplicationRunner {

    @Resource
    private SchedulerManager schedulerManager;

    @Resource
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        /*SchedulerJob schedulerJob = new SchedulerJob();
        schedulerJob.setName("量子资源详情采集");
        schedulerJob.setGroup("lzm3u8");
        schedulerManager.activeJob(schedulerJob);*/
        /*try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("host", "https://cj.lziapi.com");
            // 创建 JobDetail
            JobDetail jobDetail = JobBuilder.newJob(CollectVodDetailJob.class)
                    .withIdentity("myJob", "lzm3u8")
                    .setJobData(jobDataMap)
                    .build();

            // 创建 SimpleTrigger 以在启动时执行一次
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger", "lzm3u8")
                    .startNow() // 立即执行
                    .build();

            // 将 JobDetail 和 Trigger 添加到 Scheduler
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("Failed to schedule job: {}", e.getMessage(), e);
        }
*/
    }
}
