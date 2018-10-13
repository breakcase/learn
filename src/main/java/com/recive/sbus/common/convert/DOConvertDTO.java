package com.recive.sbus.common.convert;

public interface DOConvertDTO<S, T> {
	T convert(S s);
}
