package com.rsp.dictionary.rest.domain;

import java.util.ArrayList;
import java.util.List;

import com.rsp.dictionary.core.events.DictionaryEntry;

public class DictionaryWord {

	private String word;
	private List<String> usage = new ArrayList<String>();

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<String> getUsage() {
		return usage;
	}

	public void setUsage(List<String> usage) {
		this.usage = usage;
	}

	public DictionaryEntry toDictionaryEntry() {
		DictionaryEntry entry = new DictionaryEntry();
		entry.setWord(word);
		entry.setUsage(usage);

		return entry;
	}
	
	public static DictionaryWord fromDictionaryEntity(DictionaryEntry source) {
		DictionaryWord entry = new DictionaryWord();
		entry.setWord(source.getWord());
		entry.setUsage(source.getUsage());

		return entry;
	}

}
