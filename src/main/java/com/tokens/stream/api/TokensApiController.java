package com.tokens.stream.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokens.model.TokensResponse;
import com.tokens.model.TokensStore;
import com.tokens.stream.service.TokensFetchingService;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-18T15:17:56.845-07:00")
@Controller
public class TokensApiController implements TokensApi {

	private static final Logger log = LoggerFactory.getLogger(TokensApiController.class);

	private final HttpServletRequest request;
	@Autowired
	private TokensFetchingService tokensFetchingService;

	@org.springframework.beans.factory.annotation.Autowired
	public TokensApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.request = request;
	}

	public ResponseEntity<Map<String, String>> get(@PathVariable Long fileId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			TokensStore store = tokensFetchingService.getFile(fileId);
			Map<String, String> tokensPair = tokensFetchingService.processFile(store);
			return new ResponseEntity<Map<String, String>>(tokensPair, HttpStatus.OK);
		}
		return null;

	}

}
