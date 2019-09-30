package com.abel.example.serviceImpl;

import com.abel.example.bean.MatterHandlerVO;
import com.abel.example.bean.User;
import com.abel.example.bean.WincurservicehandlerVO;
import com.abel.example.service.HandlerService;
import com.abel.example.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019-09-14.
 */
@Slf4j
@Service
public class HandlerServiceImpl implements HandlerService {
    @Value("${httpHeaderPath}")
    private String httpHeaderPath;
    @Value("${matterHandlerPath}")
    private String matterHandlerPath;
    @Value("${wincurservicehandlerPath}")
    private String wincurservicehandlerPath;

    @Override
    public List<MatterHandlerVO> queryMatterHandlerList() {
        log.info("日志测试开始,接口方法名称:queryMatterHandlerList,当前时间:{}",new Date());
        //读取matterhandler.ashx文件并且转化为MatterHandlerVO对象
        MatterHandlerVO matterHandlerVO = new MatterHandlerVO();
        List<MatterHandlerVO> matterHandlerVOs = CommonUtil.ashxStreamToObjectList(httpHeaderPath+matterHandlerPath,null,matterHandlerVO);
        for (MatterHandlerVO handlerVO : matterHandlerVOs) {
            handlerVO.setfields();
        }
        log.info("日志测试结束,接口方法名称:queryMatterHandlerList,当前时间:{}",new Date());
        return matterHandlerVOs;
    }

    @Override
    public List<WincurservicehandlerVO> queryWincurHandlerList() {
        //读取wincurservicehandler.ashx文件并且转化为WincurservicehandlerVO对象
        WincurservicehandlerVO wincurservicehandlerVO = new WincurservicehandlerVO();
        List<WincurservicehandlerVO> wincurservicehandlerVOs = CommonUtil.ashxStreamToObjectList(httpHeaderPath+wincurservicehandlerPath,null,wincurservicehandlerVO);
        for (WincurservicehandlerVO vo : wincurservicehandlerVOs) {
            vo.setfields();
        }
        return wincurservicehandlerVOs;
    }
}
