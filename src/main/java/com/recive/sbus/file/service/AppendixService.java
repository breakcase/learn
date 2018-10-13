package com.recive.sbus.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recive.sbus.file.dao.AppendixDao;
import com.recive.sbus.file.domain.Appendix;

@Service
public class AppendixService {

	@Autowired
	private AppendixDao appendixDao;

	public boolean saveAppendix(Appendix oAppendix) {
		Appendix appendix = appendixDao.save(oAppendix);
		return appendix.getAppendixId() > 0;
	}

	/**
	 * 根据主键获取附件
	 * 
	 * @param apppendix_id
	 * @return
	 */
	public Appendix getAppendix(long apppendix_id) {
		return appendixDao.findOne(apppendix_id);
	}

}
