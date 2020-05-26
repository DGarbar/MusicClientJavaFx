package com.dharbar.musicclient.service.requester.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class Music {

	String artist;
	String songName;
	String fileUrl;
	Long trackTimeMillis;
	List<String> genres;
	List<String> tags;
}
