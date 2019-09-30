package com.abel.example.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019-09-14.
 */
@Data
@Component
public class WholeHandlerBO {
    private String code;
    private ModelBO bo;
    //取号量诗句集合,最多6条数据
    private List<MatterHandlerVO> MatterHandlerList = new ArrayList<>(6);
}
