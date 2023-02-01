package com.example.spring03.dto;

import java.time.LocalDateTime;

import com.example.spring03.domain.SoccerReply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class SoccerReplyReadDto {

	private Integer id; // 댓글 번호
	private Integer postID; // 댓글이 작성된 포스트 번호
	private String replyText; // 댓글 내용
	private String writer; // 댓글 작성자
	private LocalDateTime createdTime; // 댓글 최초 작성 시간
	private LocalDateTime modifiedTime; // 댓글 최종 수정 시간
	
	// Entity 객체에서 DTO 객체를 생성해서 리턴하는 메서드 - 객체가 생성되기 전에 사용하기 때문에 static으로 정의.
	public static SoccerReplyReadDto fromEntity(SoccerReply entity) {
		
		return SoccerReplyReadDto.builder()
				.id(entity.getId())
				.postID(entity.getPost().getId())
				.replyText(entity.getReplyText())
				.writer(entity.getWriter())
				.createdTime(entity.getCreatedTime())
				.modifiedTime(entity.getModifiedTime())
				.build();
	}
}
