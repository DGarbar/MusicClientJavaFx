package com.dharbar.musicclient.service.requester;

import com.dharbar.musicclient.service.requester.dto.Music;
import com.dharbar.musicclient.service.requester.dto.MusicAttributes;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServerRequester implements Requester {

	private final WebClient.Builder webClientBuilder;
	private ParameterizedTypeReference<List<String>> stringListType = new ParameterizedTypeReference<>() {
	};

	@Value("${server.host}")
	private String host;

	public ServerRequester(WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
	}

	@Override
	public Flux<Music> searchMusic(String search) {
		return webClientBuilder.build().get()
			.uri(buildMusic(search))
			.retrieve()
			.bodyToFlux(Music.class);
	}

	@Override
	public Mono<List<String>> searchArtists(String artist) {
		return webClientBuilder.build().get()
			.uri(buildSearch(artist))
			.retrieve()
			.bodyToMono(stringListType);
	}

	@Override
	public Flux<Music> searchByAttributes(MusicAttributes musicAttributes) {
		return null;
	}

	private URI buildSearch(String artist) {
		try {
			return new URIBuilder()
				.setScheme("http")
				.setHost(host)
				.setPath("/search")
				.addParameters(Collections.singletonList(new BasicNameValuePair("artist", artist)))
				.build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Bad search");
		}
	}

	private URI buildMusic(String search) {
		try {
			return new URIBuilder()
				.setScheme("http")
				.setHost(host)
				.setPath("/music")
				.addParameters(Collections.singletonList(new BasicNameValuePair("search", search)))
				.build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Bad music");
		}
	}
}
