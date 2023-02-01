package com.example.spring03.dto;


import com.example.spring03.domain.SoccerPosts;

import lombok.Data;

@Data
public class SoccerPostsCreateDto {

	private String title;
	private String content;
	private String author;
	private Long filesId;
	private String category;
	private Integer clickCount;
	private Integer likeCount;
	private Integer dislikeCount;
	
	public SoccerPosts toEntity() {
		return SoccerPosts.builder()
				.title(title)
				.content(content)
				.author(author)
				.category(category)
				.clickCount(0)
				.likeCount(0)
				.dislikeCount(0)
				.filesId(filesId)
				.build();
	}
}
