package com.tokens.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TokensStreams {
	String INPUT = "tokens-in";

	@Input(INPUT)
	SubscribableChannel inboundTokens();

}