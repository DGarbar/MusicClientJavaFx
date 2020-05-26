package com.dharbar.musicclient.controller.window.dto;

public enum Mood {
	THINK("think"), SAD("sad"), SLEEP("sleep"), HATE("hate"), FUN("fun"), MOVE("move"),
	SWING("swing"), CLASSIC("classic");

	private String normal;

	Mood(String normal) {
		this.normal = normal;
	}

	public String getNormal() {
		return normal;
	}
}
