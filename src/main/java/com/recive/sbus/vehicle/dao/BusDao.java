package com.recive.sbus.vehicle.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recive.sbus.vehicle.domain.Bus;

@Repository
public interface BusDao extends CrudRepository<Bus, Long> {

}
