package com.rsp.dictionary.core.rest.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rsp.dictionary.rest.domain.DictionaryWord;

public class DictionaryTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testWordSearchCanBePerformed() {
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(header);

		RestTemplate template = new RestTemplate();
		ResponseEntity<DictionaryWord> reponseEntity = template.exchange(
				"http://localhost:8080/dictionary-app/dictionary/sagamore",
				HttpMethod.GET, entity, DictionaryWord.class);
		
		assertEquals(HttpStatus.OK, reponseEntity.getStatusCode());
		
		DictionaryWord details = reponseEntity.getBody();
		
		System.out.println("word detail : " + details.getUsage().get(0));
		
		
		assertTrue(details.getUsage().size() > 0);;

	}

}
