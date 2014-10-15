package com.rsp.dictionary.config;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rsp.dictionary.core.events.QueryDetailsResposeEvent;
import com.rsp.dictionary.core.service.DictionaryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class})
public class CoreDomainIntegrationTest {
	
	@Autowired
	DictionaryService ditionary;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void springIntegrationTest() {
		QueryDetailsResposeEvent event = ditionary.requestWordDetails("test");
		assertTrue(event.getDetails() != null);
	}

}
