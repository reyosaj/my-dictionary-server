package com.rsp.dictionary.core.events;

public class QueryDetailsResposeEvent extends ResponseEvent{

	public QueryDetailsResposeEvent(Integer status) {
		super(status);
	}

	private DictionaryEntry details;
	
	public DictionaryEntry getDetails() {
		return details;
	}

	public void setDetails(DictionaryEntry details) {
		this.details = details;
	}


}
