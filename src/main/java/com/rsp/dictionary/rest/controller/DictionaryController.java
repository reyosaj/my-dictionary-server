package com.rsp.dictionary.rest.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rsp.dictionary.core.events.DictionaryEntry;
import com.rsp.dictionary.core.events.QueryDetailsResposeEvent;
import com.rsp.dictionary.core.events.ResponseUtils;
import com.rsp.dictionary.core.service.DictionaryServiceHandler;
import com.rsp.dictionary.rest.domain.DictionaryWord;
import com.rsp.dictionary.rest.domain.MatchingWords;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

	private static Logger log = Logger.getLogger(DictionaryController.class);

	@Autowired
	private DictionaryServiceHandler dictionaryService;

	@RequestMapping(method = RequestMethod.PUT, value = "/{word}")
	public ResponseEntity<DictionaryWord> addWord(@PathVariable String word,
			@RequestBody DictionaryWord details) {

		log.info("request recieved for add word " + word);

		DictionaryEntry entry = dictionaryService.addWord(details
				.toDictionaryEntry());

		return new ResponseEntity<DictionaryWord>(
				DictionaryWord.fromDictionaryEntity(entry), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{word}")
	public ResponseEntity<DictionaryWord> getDetails(@PathVariable String word) {

		log.info("request recieved for details of " + word);

		QueryDetailsResposeEvent event = dictionaryService
				.requestWordDetails(word);
		DictionaryWord details = DictionaryWord.fromDictionaryEntity(event
				.getDetails());

		return new ResponseEntity<DictionaryWord>(details,
				ResponseUtils.getHttpResponseCode(event.getStatus()));
	}

	@RequestMapping(method = RequestMethod.GET, params = { "search" })
	public ResponseEntity<MatchingWords> findMatchingWords(
			@RequestParam(value = "search") String prefix) {

		log.info("request recieved for matching words of " + prefix);

		MatchingWords matches = new MatchingWords();
		matches.setMatches(dictionaryService.requestMatchingWords(prefix));

		return new ResponseEntity<MatchingWords>(matches, HttpStatus.OK);
	}
}
