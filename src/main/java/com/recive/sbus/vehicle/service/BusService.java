package com.recive.sbus.vehicle.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recive.sbus.vehicle.dao.BusDao;
import com.recive.sbus.vehicle.domain.Bus;

@Service
public class BusService {

	@Autowired
	private BusDao busDao;

	public Bus addBus(Bus oBus) {
		return busDao.save(oBus);
	}

	public List<Bus> findAllBus() {
		Iterable<Bus> it = busDao.findAll();
		List<Bus> list = new ArrayList<Bus>();
		it.forEach(single -> {
			list.add(single);
		});
		return list;
	}

}
