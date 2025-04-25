package xyz.jdynb.dymovies.admin.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 采集 xml 列表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class CollectXmlData extends CollectData {

    /**
     * 分类节点
     */
    private Element classEl;

    /**
     * 列表数据节点
     */
    private Elements data;
}
