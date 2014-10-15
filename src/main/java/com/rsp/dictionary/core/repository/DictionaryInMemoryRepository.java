package com.rsp.dictionary.core.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.rsp.dictionary.core.domain.EntryWord;

public class DictionaryInMemoryRepository implements DictionaryRepository {

	@Autowired
	Indexer<String> indexer;
	
	private final Map<String, EntryWord> dictionary;
	
	private static final Logger logger = Logger.getLogger(DictionaryInMemoryRepository.class);

	public DictionaryInMemoryRepository() {
		dictionary = new ConcurrentHashMap<String, EntryWord>();
	}

	@Override
	public EntryWord addWordEntry(EntryWord entry) {
		if (null == entry)
			return null;

		indexer.addItem(entry.getWord().toLowerCase());
		return dictionary.put(entry.getWord().toLowerCase(), entry);

	}

	@Override
	public boolean deleteWordEntry(String word) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> findAllWordsStartingWith(String wordPrefix) {
		if (null == wordPrefix) {
			return new ArrayList<String>();
		}

		return indexer.getMatchingItems(wordPrefix);
	}

	@Override
	public EntryWord getWordDetails(String word) {
		if (null == word) {
			return null;
		}
		logger.debug("getWordDetails:requested "+ word +", dictionary size=" + dictionary.size() + " , detail=" + dictionary.get(word));
		return dictionary.get(word.toLowerCase());
	}

}
