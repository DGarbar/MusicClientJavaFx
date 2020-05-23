package com.dharbar.musicclient.service.mediaplayer;

import com.dharbar.musicclient.service.requester.dto.Music;
import java.util.List;
import java.util.function.Consumer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import org.apache.commons.collections.CollectionUtils;

public class MediaPlayerService {

	private MediaPlayer currentMediaPlayer;
	private Music currentMusic;
	private List<Music> currentPlayList;
	private Consumer<Music> onNext;
	private Runnable onLast;

	public MediaPlayerService(Consumer<Music> onNext, Runnable onLast) {
		this.onNext = onNext;
		this.onLast = onLast;
	}

	public void setPlayListAndPlay(List<Music> musics) {
		currentPlayList = musics;
		if (CollectionUtils.isNotEmpty(musics)) {
			setupNext(currentPlayList.get(0));
		}
	}

	public void play(Music music) {
		setMusic(music);
		play();
	}

	private void setMusic(Music music) {
		Media media = new Media(music.getFileUrl());
		currentMusic = music;
		if (currentMediaPlayer != null) {
			currentMediaPlayer.stop();
			currentMediaPlayer.dispose();
		}

		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(20);
		mediaPlayer.setOnEndOfMedia(() -> setupNext(music));

		mediaPlayer.seek(Duration.seconds(10));

		onNext.accept(music);
		currentMediaPlayer = mediaPlayer;
		play();
	}

	private void setupNext(Music music) {
		int i = CollectionUtils.isEmpty(currentPlayList) ? -1 : currentPlayList.indexOf(music);
		boolean isThisTheLastSong = currentPlayList.size() == i + 1;
		boolean isAnySongs = i != -1;
		if (isAnySongs && !isThisTheLastSong) {
			setMusic(currentPlayList.get(i + 1));
		}

		if (currentPlayList.size() == i + 2 || currentPlayList.size() == i + 1) {
			onLast.run();
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

	public void next() {
		if (currentMediaPlayer != null && currentMusic != null) {
			setupNext(currentMusic);
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
