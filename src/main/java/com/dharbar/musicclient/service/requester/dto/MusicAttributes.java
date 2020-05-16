package com.dharbar.musicclient.service.requester.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value(staticConstructor = "of")
public class MusicAttributes {

	private List<String> artists;
	private List<String> genres;
	private List<String> tags;
}
