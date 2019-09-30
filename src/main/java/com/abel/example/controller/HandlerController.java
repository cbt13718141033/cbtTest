package com.abel.example.controller;

import com.abel.example.bean.MatterHandlerVO;
import com.abel.example.bean.WholeHandlerBO;
import com.abel.example.bean.WincurservicehandlerVO;
import com.abel.example.service.HandlerService;
import com.abel.example.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2019-09-14.
 */
@Slf4j
@Controller
@RequestMapping(value = "/handler")
@Api(value = "行政服务大厅信息展示")
public class HandlerController {
    @Autowired
    private HandlerService handlerService;

    /**
     * 查询取号量排行前五的信息
     * api :localhost:8099/handler/queryMatterHandler
     *
     * @return
     */
    @RequestMapping(value = "/queryMatterHandler", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询取号量排行前五的信息")
    public ResponseEntity<List<MatterHandlerVO>> queryMatterHandlerList(HttpServletRequest request) {
        log.info("controller层queryMatterHandlerList接口查询开始,当前时间:{}",new Date());
        Map<String, Object> map = CommonUtil.getParameterMap(request);
        List<MatterHandlerVO> list = handlerService.queryMatterHandlerList();
        List<MatterHandlerVO> sortMatterHandlers = new ArrayList<>();
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            if (i == 5) {
                break;
            }
            sortMatterHandlers.add(list.get(i));
        }
        log.info("controller层queryMatterHandlerList接口查询结束,当前时间:{}",new Date());
        return new ResponseEntity<>(sortMatterHandlers, HttpStatus.OK);
    }

    /**
     * 查询当前窗口信息
     * api :localhost:8099/handler/queryWincurHandlerList
     *
     * @return
     */
    @RequestMapping(value = "/queryWincurHandler", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询当前窗口信息")
    public ResponseEntity<List<WincurservicehandlerVO>> queryWincurHandlerList(HttpServletRequest request) {
        Map<String, Object> map = CommonUtil.getParameterMap(request);
        List<WincurservicehandlerVO> list = handlerService.queryWincurHandlerList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/queryWholeHandler", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询取号量前6条数据")
    public ResponseEntity<WholeHandlerBO> queryWholeHandler(HttpServletRequest request) {
        WholeHandlerBO wholeHandlerBO = new WholeHandlerBO();
        List<MatterHandlerVO> matterHandlerVOs = wholeHandlerBO.getMatterHandlerList();
        return new ResponseEntity<>(wholeHandlerBO, HttpStatus.OK);
    }
}
