package com.rsp.dictionary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rsp.dictionary.core.repository.DictionaryInMemoryRepository;
import com.rsp.dictionary.core.repository.DictionaryIndexer;
import com.rsp.dictionary.core.repository.DictionaryRepository;
import com.rsp.dictionary.core.repository.Indexer;
import com.rsp.dictionary.core.service.DictionaryService;
import com.rsp.dictionary.core.service.DictionaryServiceHandler;

@Configuration
public class CoreConfig {

	@Bean
	public DictionaryService createDictionaryService(){
		return new DictionaryServiceHandler();
	}
	
	@Bean
	public DictionaryRepository createDictionaryRepository(){
		return new DictionaryInMemoryRepository(); 
	}
	
	@Bean
	public Indexer<String> createIndexer(){
		return new DictionaryIndexer();
	}
}