package com.recive.sbus.file.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recive.sbus.file.domain.Appendix;

@Repository
public interface AppendixDao extends CrudRepository<Appendix, Long> {

	final static String TABLENAME = "Appendix";// 表面对应实体名 JPA会自动映射

}
