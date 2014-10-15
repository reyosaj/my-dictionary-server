package com.rsp.dictionary.core.repository;

import java.util.List;

public interface Indexer<T> {
	public boolean addItem(T t);
	public boolean hasItem(T t);
	public T removeItem(T t);
	public List<T> getMatchingItems(T t);
}
