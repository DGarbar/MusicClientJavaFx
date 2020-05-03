package com.dharbar.musicclient.service.requester;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class Requester {

	private final WebClient.Builder webClientBuilder;
	@Value("${server.host}")
	private String host;

	public Requester(WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
	}

	public Flux<String> searchArtist(String search) {
		return webClientBuilder.build().get()
			.uri(buildSearch(search))
			.retrieve()
			.bodyToFlux(String.class);
	}

	private URI buildSearch(String search) {
		try {
			return new URIBuilder()
				.setScheme("http")
				.setHost(host)
				.setPath("/search")
				.addParameters(Collections.singletonList(new BasicNameValuePair("search", search)))
				.build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Bad search");
		}
	}
}
