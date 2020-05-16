package com.dharbar.musicclient.service.mediaplayer;

import com.dharbar.musicclient.service.requester.dto.Music;
import java.util.List;
import java.util.function.Consumer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
public class MediaPlayerService {

	private MediaPlayer currentMediaPlayer;
	private Music currentMusic;
	private List<Music> currentPlayList;

	public void setPlayListAndPlay(List<Music> musics, Consumer<Music> onNext) {
		currentPlayList = musics;
		if (CollectionUtils.isNotEmpty(musics)) {
			setupNext(currentPlayList.get(0), onNext);
		}
	}

	public void play(Music music, Consumer<Music> onNext) {
		setMusic(music, onNext);
		play();
	}

	private void setMusic(Music music, Consumer<Music> onNext) {
		Media media = new Media(music.getFileUrl());
		currentMusic = music;
		if (currentMediaPlayer != null) {
			currentMediaPlayer.stop();
			currentMediaPlayer.dispose();
		}

		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(20);
		mediaPlayer.setOnEndOfMedia(() -> setupNext(music, onNext));

		onNext.accept(music);
		currentMediaPlayer = mediaPlayer;
		play();
	}


	private void setupNext(Music music, Consumer<Music> onNext) {
		int i = CollectionUtils.isEmpty(currentPlayList) ? -1 : currentPlayList.indexOf(music);
		if (i != -1 && currentPlayList.size() != i + 1) {
			setMusic(currentPlayList.get(i + 1), onNext);
		}
	}

	public void addToPlaylist(Music music) {
		if (currentPlayList != null) {
			currentPlayList.add(music);
		}
	}

	public void removeFromPlaylist(Music music) {
		if (currentPlayList != null) {
			currentPlayList.remove(music);
		}
	}

	public boolean isPlaying() {
		return currentMediaPlayer != null && currentMediaPlayer.getStatus().equals(Status.PLAYING);
	}

	public void next(Consumer<Music> onNext) {
		if (currentMediaPlayer != null && currentMusic != null) {
			setupNext(currentMusic, onNext);
		}
		play();
	}

	public void play() {
		if (currentMediaPlayer != null) {
			currentMediaPlayer.play();
		}
	}

	public void pause() {
		if (currentMediaPlayer != null) {
			currentMediaPlayer.pause();
		}
	}

	public void stop() {
		if (currentMediaPlayer != null) {
			currentMediaPlayer.stop();
		}
	}
}
