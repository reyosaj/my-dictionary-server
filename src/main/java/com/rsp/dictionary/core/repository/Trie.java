package com.rsp.dictionary.core.repository;

import java.util.ArrayList;
import java.util.List;

public class Trie {

	private TrieNode root;

	public static class TrieNode {
		public char value;
		public boolean isWord;
		public TrieNode[] children = new TrieNode[26];
		public TrieNode parent;
	}

	public Trie(char value) {
		root = new TrieNode();
		root.value = value;
	}

	public boolean addWord(String word) {
		if (null == word || word.trim().length() == 0) {
			return false;
		}

		add(root, word);

		return true;
	}

	private void add(TrieNode root, String word) {
		if (word.equals("")) {
			root.isWord = true;
			return;
		}

		char c = word.charAt(0);
		int index = getIndex(c);

		if (index >= 0 && index < 26) { // consider only alphabets
			if (null == root.children[index]) {
				TrieNode node = new TrieNode();
				node.value = c;
				node.parent = root;
				root.children[index] = node;
			}

			add(root.children[index], word.substring(1));
		}

	}

	private int getIndex(char c) {
		int index = c - 97;// ascii('a') = 97
		if (c >= 65 && c <= 90) { // upper case
			index = c - 65;
		}
		return index;
	}

	public List<String> getAllWords() {
		List<String> words = new ArrayList<String>();
		getWords(root, words, "");
		return words;
	}

	public TrieNode findPrefixNode(TrieNode root, String prefix) {
		if (null == root || null == prefix || prefix.trim().length() == 0) {
			return null;
		}
		TrieNode prefixNode = root;
		for (char c : prefix.toCharArray()) {
			int index = getIndex(c);
			if (index < 0 || index >= 26 || prefixNode.children[index] == null) {
				break;
			}
			prefixNode = prefixNode.children[index];
		}

		return prefixNode;
	}

	/**
	 * @param root
	 * @param words
	 * @param prefix
	 */
	public void getWords(TrieNode root, List<String> words, String prefix) {
		if (null == root) {
			return;
		}
		if (root.value != ' ') {
			prefix += root.value;
		}
		if (root.isWord) {
			words.add(prefix);
		}
		for (TrieNode child : root.children) {
			getWords(child, words, prefix);
		}
	}

	public boolean remove(String word) {
		return false;// TODO implement
	}

	public TrieNode getRoot() {
		return root;
	}

}
