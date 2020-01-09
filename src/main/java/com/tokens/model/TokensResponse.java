package com.tokens.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TokensResponse<K, V> implements Cloneable, Serializable {
	private static final long serialVersionUID = 8964159876945766899L;
	private TokenStructure<K, V>[] store;
	private static final int INITIAL_CAPACITY = 1 << 4;
	private int size = 0;
	private Set<Entry<K, V>> entrySet;

	public TokensResponse() {
		this(INITIAL_CAPACITY);
	}

	public TokensResponse(int initialCapacity) {
		this.store = new TokenStructure[initialCapacity];
	}

	public void insertToken(K key, V value) {
		TokenStructure<K, V> token = new TokenStructure<>(key, value, null);

		int indexOfStore = getHash(key) % getBucketSize();
		TokenStructure<K, V> existingToken = store[indexOfStore];
		if (existingToken == null) {
			store[indexOfStore] = token;
			size++;
		} else {

			while (existingToken.nextToken != null) {
				if (existingToken.key.equals(key)) {
					existingToken.value = value;
					return;
				}
				existingToken = existingToken.nextToken;
			}

			if (existingToken.key.equals(key)) {
				existingToken.value = value;
			} else {
				existingToken.nextToken = token;
				size++;
			}
		}
	}

	public V fetchToken(K key) {
		int indexOfStore = getHash(key) % getBucketSize();
		TokenStructure<K, V> existingToken = store[indexOfStore];

		if (existingToken.key.equals(key)) {
			return existingToken.value;
		} else {
			while (existingToken != null) {
				if (existingToken.key.equals(key)) {
					return existingToken.value;
				}
				existingToken = existingToken.nextToken;
			}
		}

		return null;
	}

	public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> es;
        return (es = entrySet) == null ? (entrySet = new TokenEntrySet()) : es;
    }

	private int getBucketSize() {
		return INITIAL_CAPACITY;
	}

	private int getHash(K key) {
		int hash = 7;
		hash = 31 * hash + (key == null ? 0 : key.hashCode());
		return hash;
	}

}
