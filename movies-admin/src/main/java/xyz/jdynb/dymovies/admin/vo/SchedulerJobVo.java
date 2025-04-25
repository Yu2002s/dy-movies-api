package xyz.jdynb.dymovies.admin.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import xyz.jdynb.dymovies.admin.pojo.SchedulerJob;

import java.util.List;

/**
 * 任务
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class SchedulerJobVo extends SchedulerJob {

    /**
     * 0: 状态为空；1：运行中；2：暂停；3：已完成；4：已失败；5：已堵塞
     */
    private int status;

    private String statusStr;

    private List<SchedulerJobVo> children;

    public String getKey() {
        return group == null ? name : group + "." + name;
    }

    /**
     * 获取状态
     * @return 状态文本
     */
    public String getStatusStr() {
        return switch (status) {
            case 1 -> "运行中";
            case 2 -> "已暂停";
            case 3 -> "已完成";
            case 4 -> "已失败";
            case 5 -> "已堵塞";
            default -> "";
        };
    }
}