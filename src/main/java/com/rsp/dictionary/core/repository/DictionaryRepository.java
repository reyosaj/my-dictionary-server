package com.rsp.dictionary.core.repository;

import java.util.List;

import com.rsp.dictionary.core.domain.EntryWord;

public interface DictionaryRepository {
	
	public EntryWord addWordEntry(EntryWord entry);
	public boolean deleteWordEntry(String word);
	public List<String> findAllWordsStartingWith(String wordPrefix);
	public EntryWord getWordDetails(String word);

}
