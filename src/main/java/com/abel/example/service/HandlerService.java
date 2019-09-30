package com.abel.example.service;

import com.abel.example.bean.MatterHandlerVO;
import com.abel.example.bean.WincurservicehandlerVO;

import java.util.List;

/**
 * The Interface HandlerService.
 */
public interface HandlerService {

	/**
	 * 查询取号量对象集合
	 * @return
     */
	public List<MatterHandlerVO> queryMatterHandlerList();

	/**
	 * 查询当前窗口信息
	 * @return
     */
	public List<WincurservicehandlerVO> queryWincurHandlerList();


}
