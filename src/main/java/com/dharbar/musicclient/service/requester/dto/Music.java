package com.dharbar.musicclient.service.requester.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class Music {

	String artist;
	String songName;
	String fileUrl;
	Long trackTimeMillis;
	List<String> genres;
	List<String> tags;
}
