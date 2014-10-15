package com.rsp.dictionary.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rsp.dictionary.core.domain.EntryWord;
import com.rsp.dictionary.core.events.DictionaryEntry;
import com.rsp.dictionary.core.events.QueryDetailsResposeEvent;
import com.rsp.dictionary.core.events.ResponseEvent;
import com.rsp.dictionary.core.repository.DictionaryRepository;

public class DictionaryServiceHandler implements DictionaryService {

	@Autowired
	DictionaryRepository repository;

	@Override
	public DictionaryEntry addWord(DictionaryEntry element) {
		if (null == element)
			return null;

		EntryWord entry = EntryWord.fromDictionaryEntry(element);
		repository.addWordEntry(entry);
		return entry.toDictionaryEntry();
	}

	@Override
	public DictionaryEntry deleteWord(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> requestMatchingWords(String prefix) {
		return repository.findAllWordsStartingWith(prefix);
	}

	@Override
	public QueryDetailsResposeEvent requestWordDetails(String word) {

		QueryDetailsResposeEvent event = null;

		if (word == null || word.trim().equals("")) {
			event = new QueryDetailsResposeEvent(ResponseEvent.INVALID_PARAM);
			return event;
		}

		EntryWord entry = repository.getWordDetails(word);

		if (null == entry) {
			event = new QueryDetailsResposeEvent(ResponseEvent.NO_SUCH_ELEMENT);
			event.setDetails(DictionaryEntry.noEntry(word));
		} else {
			event = new QueryDetailsResposeEvent(ResponseEvent.SUCCESS);
			event.setDetails(entry.toDictionaryEntry());
		}

		return event;
	}

}
