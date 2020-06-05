package com.dharbar.musicclient.controller.window.dto;

public enum Genre {
	ALTERNATIVE("Alternative"),
	BLUES("Blues"),
	CLASSICAL("Classical"),
	COMEDY("Comedy"),
	COUNTRY("Country"),
	DANCE("Dance"),
	ELECTRONIC("Electronic"),
	FITNESS_AND_WORKOUT("Fitness & Workout"),
	HIP_HOP_RAP("Hip-Hop/Rap"),
	UNDERGROUND_RAP("Underground Rap"),
	JAZZ("Jazz"),
	K_POP("K-Pop"),
	LATINO("Latino"),
	METAL("Metal"),
	POP("Pop"),
	R_AND_B_SOUL("R&B/Soul"),
	REGGAE("Reggae"),
	ROCK("Rock"),
	SINGER_SONGWRITER("Singer/Songwriter"),
	SOUNDTRACK("Soundtrack");

	private String normal;

	Genre(String normal) {
		this.normal = normal;
	}

	public String getNormal() {
		return normal;
	}
}
