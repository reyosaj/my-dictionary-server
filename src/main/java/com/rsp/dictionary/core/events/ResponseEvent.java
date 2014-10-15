package com.rsp.dictionary.core.events;

public class ResponseEvent {

	public static final int SUCCESS = 0;
	public static final int NO_SUCH_ELEMENT = 1;
	public static final int INVALID_PARAM = 2;
	public static final int INTERNAL_ERROR = 3;

	private Integer status;

	public ResponseEvent(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
