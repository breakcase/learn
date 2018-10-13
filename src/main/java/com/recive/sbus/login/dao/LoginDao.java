package com.recive.sbus.login.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.recive.sbus.user.domain.User;

@Repository
public interface LoginDao extends CrudRepository<User, Long> {

	final static String TABLENAME = "User";

	public List<User> findByUsernameAndPassword(String username, String password);

	@Modifying
	@Transactional
	@Query("update " + TABLENAME + " as a set a.password = ?2 where a.username = ?1 ")
	public int changePwd(String username, String password);

	@Modifying
	@Transactional
	@Query("update " + TABLENAME + " as a set a.lastLoginTime = ?2,a.loginCount = ?3 where a.username = ?1 ")
	public int updateLoginTimeAndCount(String username, Date date, int count);
}
