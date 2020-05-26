package com.dharbar.musicclient.service.requester;

import com.dharbar.musicclient.service.requester.dto.Music;
import com.dharbar.musicclient.service.requester.dto.MusicAttributes;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Requester {

	Flux<Music> searchMusic(String search);

	Mono<List<String>> searchArtists(String artist);

	Flux<Music> searchByAttributes(MusicAttributes musicAttributes);

	Mono<String> updateMusic(Music music);
}
