/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.10).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.tokens.stream.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tokens.model.ErrorResponse;
import com.tokens.model.TokensResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-18T15:17:56.845-07:00")

@Api
public interface TokensApi {

	@ApiOperation(value = "", nickname = "get", notes = "Return a pair from tokens list", response = TokensResponse.class, responseContainer = "List", tags = {
			"tokens", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = TokensResponse.class, responseContainer = "List"),
			@ApiResponse(code = 200, message = "Error", response = ErrorResponse.class) })
	@RequestMapping(value = "/{fileId}", produces = { "application/json" }, consumes = { "application/json",
			"multipart/form-data" }, method = RequestMethod.GET)
	ResponseEntity<Map<String, String>> get(Long fileId);
}
