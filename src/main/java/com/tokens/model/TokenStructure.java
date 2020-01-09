package com.tokens.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenStructure<K, V> {
	final K key;
	V value;
	TokenStructure<K, V> nextToken;
}
