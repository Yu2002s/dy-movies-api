package xyz.jdynb.dymovies.enums;

import lombok.Getter;

/**
 * 主页分类
 */
@Getter
public enum HomeCate {
    UPDATE(0, "最近更新");

    final Integer cateId;
    final String name;

    HomeCate(Integer cateId, String name) {
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
