package com.rsp.dictionary.core.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rsp.dictionary.config.CoreConfig;
import com.rsp.dictionary.core.events.DictionaryEntry;
import com.rsp.dictionary.core.events.QueryDetailsResposeEvent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={CoreConfig.class})
public class DictionaryServiceHandlerTest {

	@Autowired
	DictionaryService dictionary;

	private static Map<String, String> words = new HashMap<String, String>();

	public DictionaryServiceHandlerTest() {
		words.put("Washer", "a washer");
		words.put("Washing", "washing the car");
		words.put("Unnumbered", "not numbered");
		words.put("Agedness", "being old");
		words.put("Deaconess", " dont know");
		words.put("Age", "years since birth");
	}

	@Before
	public void setUp() throws Exception {
		Iterator<Map.Entry<String, String>> it = words.entrySet().iterator();
		while (it.hasNext()) {
			DictionaryEntry word = new DictionaryEntry();
			Map.Entry<String, String> entry = it.next();
			ArrayList<String> usage = new ArrayList<String>();
			usage.add(entry.getValue());
			word.setWord(entry.getKey());
			word.setUsage(usage);

			dictionary.addWord(word);
		}

	}

	@Test
	public void testAddWord() {
		DictionaryEntry word = new DictionaryEntry();
		word.setWord("vola");
		word.setUsage(new ArrayList<String>());
		dictionary.addWord(word);
		
		assertTrue(dictionary.requestWordDetails("vola") != null);
	}

	@Test
	public void testRequestMatchingWords() {
		List<String> words = dictionary.requestMatchingWords("Was");
		
		assertTrue(words.contains("Washer"));
		assertTrue(words.contains("Washing"));
	}

	@Test
	public void testRequestWordDetails() {
		QueryDetailsResposeEvent event = dictionary.requestWordDetails("Deaconess");
		DictionaryEntry wordDetail = event.getDetails();
		assertTrue(wordDetail.getWord().equals("Deaconess"));
		assertTrue(wordDetail.getUsage().size() > 0);
	}

}
