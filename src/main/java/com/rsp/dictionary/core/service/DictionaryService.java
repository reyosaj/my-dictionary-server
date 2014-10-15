package com.rsp.dictionary.core.service;

import java.util.List;

import com.rsp.dictionary.core.events.DictionaryEntry;
import com.rsp.dictionary.core.events.QueryDetailsResposeEvent;

public interface DictionaryService {
	public 	DictionaryEntry addWord(DictionaryEntry entry);
	public DictionaryEntry deleteWord(String word);
	public List<String> requestMatchingWords(String prefix);
	public QueryDetailsResposeEvent requestWordDetails(String word);
	
}
