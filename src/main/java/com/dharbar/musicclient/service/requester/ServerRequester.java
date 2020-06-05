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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServerRequester implements Requester {

	private final WebClient.Builder webClientBuilder;
	private ParameterizedTypeReference<List<String>> stringListType = new ParameterizedTypeReference<>() {
	};

	@Value("${music-library.server.host}")
	private String host;

	public ServerRequester(WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
	}

	@Override
	public Flux<Music> searchMusicByArtist(String search) {
		return webClientBuilder.build().get()
			.uri(buildSearchMusicByAuthor(search))
			.accept(MediaType.APPLICATION_STREAM_JSON)
			.retrieve()
			.bodyToFlux(Music.class);
	}

	@Override
	public Mono<List<String>> searchArtists(String artist) {
		return webClientBuilder.build().get()
			.uri(buildSearchArtist(artist))
			.accept(MediaType.APPLICATION_STREAM_JSON)
			.retrieve()
			.bodyToMono(stringListType);
	}

	@Override
	public Flux<Music> searchByAttributes(MusicAttributes musicAttributes) {
		return webClientBuilder.build().post()
			.uri(buildSearchMusicAttributes())
			.accept(MediaType.APPLICATION_STREAM_JSON)
			.bodyValue(musicAttributes)
			.retrieve()
			.bodyToFlux(Music.class);
	}

	@Override
	public Mono<String> updateMusic(Music music) {
		return webClientBuilder.build().post()
			.uri(buildMusicAdd())
			.bodyValue(music)
			.retrieve()
			.onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), ClientResponse::createException)
			.bodyToMono(String.class);
	}

	private URI buildSearchMusicAttributes() {
		try {
			return new URIBuilder()
				.setScheme("http")
				.setHost(host)
				.setPath("/pref/music")
				.build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Bad search");
		}
	}

	private URI buildSearchArtist(String artist) {
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

	private URI buildMusicAdd() {
		try {
			return new URIBuilder()
				.setScheme("http")
				.setHost(host)
				.setPath("/pref/new")
				.build();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Bad music");
		}
	}

	private URI buildSearchMusicByAuthor(String search) {
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
