package com.recive.sbus.common.convert;

public interface DTOConvertDO<S, T> {
	T convert(S s);
}
