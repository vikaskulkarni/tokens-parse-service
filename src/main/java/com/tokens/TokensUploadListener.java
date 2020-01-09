package com.tokens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.tokens.config.TokensStreams;
import com.tokens.model.EventToken;
import com.tokens.model.TokensStore;
import com.tokens.stream.service.TokensFetchingService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokensUploadListener {
	@Autowired
	private TokensFetchingService tokensFetchingService;

	@StreamListener(TokensStreams.INPUT)
	public void handleTokens(@Payload EventToken token) {
		log.info("Received tokens: {}", token.getTimestamp());
		TokensStore store = tokensFetchingService.getFile(token.getFileId());
		System.out.println();
	}
}
