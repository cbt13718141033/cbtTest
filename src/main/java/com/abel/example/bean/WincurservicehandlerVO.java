package com.abel.example.bean;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by Administrator on 2019-09-13.
 */
@Data
public class WincurservicehandlerVO extends  ModelBO {
    private String windowCode;//窗口编码
    private String windowName;//窗口名称
    private String businessId;//事项id
    private String businessName;//事项名称
    private String identityCard;//身份证号码
    private String userName;//姓名
    private String startTime;//开始时间
    private String 窗口编码;
    private String 窗口名称;
    private String 事项ID;
    private String 事项名称;
    private String 身份证号;
    private String 姓名;
    private String 开始时间;

    public void  setfields(){
        if (!StringUtils.isBlank(this.窗口编码)){
            this.windowCode=this.窗口编码;
        }
        if (!StringUtils.isBlank(this.窗口名称)){
            this.windowName=this.窗口名称;
        }
        if (!StringUtils.isBlank(this.事项ID)){
            this.businessId=this.事项ID;
        }
        if (!StringUtils.isBlank(this.事项名称)){
            this.businessName=this.事项名称;
        }
        if (!StringUtils.isBlank(this.身份证号)){
            this.identityCard=this.身份证号;
        }
        if (!StringUtils.isBlank(this.姓名)){
            this.userName=this.姓名;
        }
        if (!StringUtils.isBlank(this.开始时间)){
            this.startTime=this.开始时间;
        }
    }
}
