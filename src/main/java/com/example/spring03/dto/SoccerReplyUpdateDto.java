package com.example.spring03.dto;

import com.example.spring03.domain.SoccerReply;

import lombok.Data;

@Data
public class SoccerReplyUpdateDto {

	private Integer replyId;
	private String replyText;
	
	public SoccerReply toEntity() {
		return SoccerReply.builder().id(replyId).replyText(replyText).build();
	}
}
