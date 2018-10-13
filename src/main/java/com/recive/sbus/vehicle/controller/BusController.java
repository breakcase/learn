package com.recive.sbus.vehicle.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recive.sbus.common.BaseVo;
import com.recive.sbus.common.CommonConstants;
import com.recive.sbus.vehicle.domain.Bus;
import com.recive.sbus.vehicle.service.BusService;

@Controller
@RequestMapping(value = "/bus")
public class BusController {
	@Autowired
	private BusService oBusService;

	@ResponseBody
	@RequestMapping(value = "/add", produces = { "application/json;charset=UTF-8" })
	public BaseVo<Bus> addBus(@Valid Bus oBus) {

		Bus bus = oBusService.addBus(oBus);
		BaseVo<Bus> vo = new BaseVo<Bus>();
		if (bus.getBusid() <= 0) {
			vo.setCode(CommonConstants.FAILED_CODE);
			vo.setMsg(CommonConstants.FAILED_MSG);
		}
		return vo;
	}

	@ResponseBody
	@RequestMapping(value = "/all", produces = { "application/json;charset=UTF-8" })
	public BaseVo<Object> findAllBus() {
		BaseVo<Object> vo = new BaseVo<Object>();
		List<?> list = oBusService.findAllBus();

		vo.setDatas(list);

		return vo;
	}

}
