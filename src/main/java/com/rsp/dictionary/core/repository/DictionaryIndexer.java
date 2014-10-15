package com.rsp.dictionary.core.repository;

import java.util.ArrayList;
import java.util.List;

import com.rsp.dictionary.core.repository.Trie.TrieNode;

public class DictionaryIndexer implements Indexer<String> {

	private final Trie index = new Trie(' ');
	
	@Override
	public boolean addItem(String word) {
		return index.addWord(word);
	}

	@Override
	public boolean hasItem(String t) {
		TrieNode node = index.findPrefixNode(index.getRoot(), t);
		return (node != null);
	}

	@Override
	public String removeItem(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMatchingItems(String prefix) {
		TrieNode node = index.findPrefixNode(index.getRoot(), prefix);
		List<String> words = new ArrayList<String>();
		index.getWords(node, words, prefix.substring(0, prefix.length() - 1));
		return words;
	}

}
