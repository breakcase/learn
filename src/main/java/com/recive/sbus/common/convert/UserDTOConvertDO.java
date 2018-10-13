package com.recive.sbus.common.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.recive.sbus.user.domain.User;
import com.recive.sbus.user.domain.dto.UserDTO;

@Component
public class UserDTOConvertDO implements DTOConvertDO<UserDTO, User> {

	@Override
	public User convert(UserDTO userDTO) {
		User oUser = new User();
		BeanUtils.copyProperties(userDTO, oUser);
		return oUser;
	}

}
