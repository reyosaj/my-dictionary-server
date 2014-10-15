package com.rsp.dictionary.core.events;

import org.springframework.http.HttpStatus;

public class ResponseUtils {

	public static HttpStatus getHttpResponseCode(Integer eventStatus) {

		switch (eventStatus) {
		case ResponseEvent.NO_SUCH_ELEMENT:
			return HttpStatus.NOT_FOUND;
		case ResponseEvent.INVALID_PARAM:
			return HttpStatus.BAD_REQUEST;
		case ResponseEvent.INTERNAL_ERROR:
			return HttpStatus.INTERNAL_SERVER_ERROR;
		default:
			return HttpStatus.OK;
		}
	}
}
