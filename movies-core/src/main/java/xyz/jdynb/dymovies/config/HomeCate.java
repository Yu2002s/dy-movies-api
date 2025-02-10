package xyz.jdynb.dymovies.config;

import lombok.Getter;

/**
 * 主页分类
 */
@Getter
public enum HomeCate {
    UPDATE("update", "最近更新");

    final String cateId;
    final String name;

    HomeCate(String cateId, String name) {
        this.cateId = cateId;
        this.name = name;
    }

    public HomeCate getHomeCate() {
        for (HomeCate cate : HomeCate.values()) {
            if (cate.cateId.equals(this.cateId)) {
                return cate;
            }
        }
        return HomeCate.UPDATE;
    }
}
