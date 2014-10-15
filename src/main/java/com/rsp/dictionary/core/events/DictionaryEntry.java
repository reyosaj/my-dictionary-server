package com.rsp.dictionary.core.events;

import java.util.ArrayList;
import java.util.List;

public class DictionaryEntry {

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

	public static DictionaryEntry noEntry(String word) {
		DictionaryEntry entry = new DictionaryEntry();
		entry.setWord(word);
		return entry;
	}
}
