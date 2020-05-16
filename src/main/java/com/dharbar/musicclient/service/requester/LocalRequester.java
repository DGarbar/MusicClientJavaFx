package com.dharbar.musicclient.service.requester;

import com.dharbar.musicclient.service.requester.dto.Music;
import com.dharbar.musicclient.service.requester.dto.MusicAttributes;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Profile("local")
@Primary
@Service
public class LocalRequester implements Requester {

	private ObjectMapper mapper;

	@Override
	public Flux<Music> searchMusic(String search) {
		return Flux.just(
			Music.builder()
				.artist("test")
				.songName("songNameTest")
				.fileUrl(
					"https://audio-ssl.itunes.apple.com/itunes-assets/Music7/v4/c0/5c/4a/c05c4ab6-84dc-f2f0-70a8-3640d577cf07/mzaf_3410420486333941121.plus.aac.p.m4a")
				.genres(List.of("testGenre1", "testGenre2"))
				.build(),
			Music.builder()
				.artist("test1")
				.songName("songNameTest1")
				.fileUrl(
					"https://audio-ssl.itunes.apple.com/itunes-assets/Music/30/00/67/mzm.pdnjufij.aac.p.m4a")
				.genres(List.of("testGenre2"))
				.build());
	}

	@Override
	public Flux<Music> searchByAttributes(MusicAttributes musicAttributes){
		return Flux.just(
			Music.builder()
				.artist("test111111")
				.songName("songName11111111Test")
				.fileUrl(
					"https://audio-ssl.itunes.apple.com/itunes-assets/Music7/v4/c0/5c/4a/c05c4ab6-84dc-f2f0-70a8-3640d577cf07/mzaf_3410420486333941121.plus.aac.p.m4a")
				.genres(List.of("testGenre11111", "testGenre2111"))
				.build(),
			Music.builder()
				.artist("test11111")
				.songName("songNameTest11111")
				.fileUrl(
					"https://audio-ssl.itunes.apple.com/itunes-assets/Music/30/00/67/mzm.pdnjufij.aac.p.m4a")
				.genres(List.of("testGenre21221313123"))
				.build());
	}

	@Override
	public Mono<List<String>> searchArtists(String artist) {
		return Mono.just(List.of("test1", "test2", "test3"));
	}
}
