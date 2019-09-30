package com.abel.example.bean;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;

/**
 * Created by Administrator on 2019-09-13.
 * "业务名称": "自助网办③",
 * "事项名称": "其他事项",
 * "总取号量": "2",
 * "已办理数量": "0",
 * "未办理数量": "2",
 * "等候时间": "0"
 */
@Data
public class MatterHandlerVO extends ModelBO implements Comparable<MatterHandlerVO> {
    private String businessName;
    private String matterName;
    private long offerNum;//总取号数量
    private long processed;//已办理数量
    private long unprocess;//未办理数量
    private long waitTime;//等待时间
    private String 业务名称;
    private String 事项名称;
    private String 总取号量;
    private String 已办理数量;
    private String 未办理数量;
    private String 等候时间;

    public void setfields() {
        if (!StringUtils.isBlank(this.业务名称)) {
            this.businessName = this.业务名称;
        }
        if (!StringUtils.isBlank(this.事项名称)) {
            this.matterName = this.事项名称;
        }
        if (!StringUtils.isBlank(this.总取号量)) {
            this.offerNum = Long.parseLong(this.总取号量);
        }
        if (!StringUtils.isBlank(this.已办理数量)) {
            this.processed = Long.parseLong(this.已办理数量);
        }
        if (!StringUtils.isBlank(this.未办理数量)) {
            this.unprocess = Long.parseLong(this.未办理数量);
        }
        if (!StringUtils.isBlank(this.等候时间)) {
            this.waitTime = Long.parseLong(this.等候时间);
        }
    }

    /**
     * 按照总取号量进行排序
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(MatterHandlerVO o) {
        if (this.getOfferNum() > o.getOfferNum()) {
            return -1;
        } else if (this.getOfferNum() == o.getOfferNum()) {
            return 0;
        } else {
            return 1;
        }
    }
}
