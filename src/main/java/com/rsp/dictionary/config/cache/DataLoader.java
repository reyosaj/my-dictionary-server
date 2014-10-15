package com.rsp.dictionary.config.cache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.ListIterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rsp.dictionary.core.events.DictionaryEntry;
import com.rsp.dictionary.core.service.DictionaryService;
import com.rsp.dictionary.core.service.DictionaryServiceHandler;

public class DataLoader implements ServletContextListener {

	public static final Logger logger = Logger.getLogger(DataLoader.class);

	private DictionaryService dictionaryService;

	private String dataFile = "dictionary.json";

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.debug("Preparing Data preLoad");

		ServletContext sc = event.getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(sc);
		dictionaryService = appContext.getBean(DictionaryServiceHandler.class);

		new Thread((Runnable) new LoaderTask()).start();
	}

	class LoaderTask implements Runnable {

		@SuppressWarnings("unchecked")
		@Override
		public void run() {

			JSONParser parser = new JSONParser();

			JSONArray readJson;
			try {

				Resource resource = new ClassPathResource(dataFile);
				logger.debug("resource file name : " + resource.getFilename()
						+ " , uri :" + resource.getURI());

				Reader reader = new InputStreamReader(resource.getInputStream());

				readJson = (JSONArray) parser.parse(reader);

				ListIterator<JSONObject> it = readJson.listIterator();

				while (it.hasNext()) {
					JSONObject entry = (JSONObject) it.next();
					
					String word = (String) entry.get("word");
					String[] usage = { (String) entry.get("meaning") };
					
					DictionaryEntry ele = new DictionaryEntry();
					ele.setWord(word.toLowerCase());
					ele.setUsage(Arrays.asList(usage));
					
					dictionaryService.addWord(ele);
				}
			} catch (FileNotFoundException e) {
				logger.error("Input file not found" + e.getMessage());
			} catch (ParseException e) {
				logger.error("Input file could not be parsed" + e.getMessage());
			} catch (IOException e) {
				logger.error("IO exception in parsing" + e.getMessage());
			}
		}

	}

}
