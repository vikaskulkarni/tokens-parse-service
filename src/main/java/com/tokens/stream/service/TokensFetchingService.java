package com.tokens.stream.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokens.model.TokensResponse;
import com.tokens.model.TokensStore;
import com.tokens.repository.TokensFileRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TokensFetchingService {
	@Autowired
	private TokensFileRepo tokensFileRepo;

	public TokensStore getFile(String externalFileId) {
		try {
			return tokensFileRepo.findByExternalId(externalFileId)
					.orElseThrow(() -> new FileNotFoundException("File not found with id " + externalFileId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, String> processFile(TokensStore store) {
		File tokensFile = new File("tmpFile");
		try {
			OutputStream os = new FileOutputStream(tokensFile);
			os.write(store.getToken_file_content());
			os.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		Map<String, String> tokenMapping = new LinkedHashMap<String, String>();

		try (Stream<String> stream = Files.lines(Paths.get(tokensFile.toPath().toUri()))) {

			tokenMapping = stream.filter(line -> line.matches("^\\w+=\\w+$"))
					.collect(Collectors.toMap(s -> s.split("=")[0], s -> s.split("=")[1]));

			log.info((Arrays.toString(tokenMapping.entrySet().toArray())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenMapping;
	}
}
