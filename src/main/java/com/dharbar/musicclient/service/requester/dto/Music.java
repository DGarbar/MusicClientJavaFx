package com.dharbar.musicclient.service.requester.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class Music {

	private String artist;
	private String songName;
	private String fileUrl;
	private Long trackTimeMillis;
	private List<String> genres;
}
