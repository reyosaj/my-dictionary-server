package com.rsp.dictionary.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.rsp.dictionary.core.events.DictionaryEntry;

public class EntryWord {

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

	public static EntryWord fromDictionaryEntry(DictionaryEntry entry) {
		if (null == entry)
			return null;
		EntryWord word = new EntryWord();
		word.setWord(entry.getWord());
		word.setUsage(entry.getUsage());

		return word;
	}

	public DictionaryEntry toDictionaryEntry() {
		DictionaryEntry entry = new DictionaryEntry();
		entry.setWord(word);
		entry.setUsage(usage);

		return entry;
	}
}
