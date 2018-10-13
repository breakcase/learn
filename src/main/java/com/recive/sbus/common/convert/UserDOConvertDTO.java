package com.recive.sbus.common.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.recive.sbus.user.domain.User;
import com.recive.sbus.user.domain.dto.UserDTO;

@Component
public class UserDOConvertDTO implements DOConvertDTO<User, UserDTO> {

	@Override
	public UserDTO convert(User user) {
		UserDTO oUserDTO = new UserDTO();
		BeanUtils.copyProperties(user, oUserDTO);
		return oUserDTO;
	}

}
