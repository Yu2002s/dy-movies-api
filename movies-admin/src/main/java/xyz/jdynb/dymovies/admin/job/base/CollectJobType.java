package xyz.jdynb.dymovies.admin.job.base;

/**
 * 采集 Job 类型
 */
public enum CollectJobType {

    /**
     * 对应采集的 ac=list
     */
    LIST("list"),
    /**
     * 对应采集的 ac=detail
     */
    DETAIL("detail");

    final String name;

    CollectJobType(String name) {
        this.name = name;
    }

    public CollectJobType getJobType(String name) {
        for (CollectJobType collectJobType : CollectJobType.values()) {
            if (collectJobType.name.equals(name)) {
                return collectJobType;
            }
        }
        return null;
    }
}