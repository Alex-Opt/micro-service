package com.ly.mt.cabinet.c.wechat.entity;

import java.util.UUID;

public class UUIDUtils {

	public static String createUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
