package com.dharbar.musicclient.service.subscriber;

import com.dharbar.musicclient.service.requester.dto.Music;
import java.util.function.Consumer;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.SignalType;

public class MusicSubscriber extends BaseSubscriber<Music> {
	final int limit = 2;
	final Consumer<Music> onNext;

	public MusicSubscriber(Consumer<Music> onNext) {
		this.onNext = onNext;
	}

	public void req(){
		request(limit);
	}

	@Override
	protected void hookOnSubscribe(Subscription subscription) {
		request(limit);
	}

	@Override
	protected void hookOnNext(Music value) {
		onNext.accept(value);
	}

	@Override
	protected void hookFinally(SignalType type) {
		super.hookFinally(type);
	}
}
