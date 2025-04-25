package xyz.jdynb.dymovies.admin.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.ToString;
import xyz.jdynb.dymovies.admin.validator.UpdateJobStatusGroup;

/**
 * 任务
 */
@Data
@ToString
public class SchedulerJob {

    private String key;

    @NotNull(message = "请输入任务名称", groups = {Default.class, UpdateJobStatusGroup.class})
    @NotEmpty(message = "请输入任务名称", groups = {Default.class, UpdateJobStatusGroup.class})
    protected String name;

    @NotNull(message = "请输入任务分组", groups = {Default.class, UpdateJobStatusGroup.class})
    @NotEmpty(message = "请输入任务分组", groups = {Default.class, UpdateJobStatusGroup.class})
    protected String group;

    @NotNull(message = "请输入cron表达式")
    @NotEmpty(message = "请输入cron表达式")
    private String cron;

    @NotNull(message = "请输入任务类")
    @NotEmpty(message = "请输入任务类")
    private String jobClass;

    @NotNull(message = "请输入任务描述")
    @NotEmpty(message = "请输入任务描述")
    private String desc;

    @NotNull(message = "请输入采集地址")
    @NotEmpty(message = "请输入采集地址")
    private String url;
}