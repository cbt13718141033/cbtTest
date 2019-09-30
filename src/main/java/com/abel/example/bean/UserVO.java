package com.abel.example.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2019-09-11.
 */
@Data
public class UserVO extends  ModelBO{
    private Long id;
    private String code;
    private String name;
    private String username;
    private String email;
    private String telephone;
    private Date createTime;
    private String createTimeStr;
}