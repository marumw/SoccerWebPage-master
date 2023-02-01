package com.example.spring03.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity(name = "SOCCER_POSTS")
@SequenceGenerator(name = "SOCCERPOSTS_SEQ_GEN", sequenceName = "SOCCERPOSTS_SEQ", initialValue = 1, allocationSize = 1)
public class SoccerPosts extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOCCERPOSTS_SEQ_GEN")
	private Integer id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private String author;
	
	@Column(columnDefinition = "integer default 0", nullable = false)
	private Integer clickCount;
	
	@Column(columnDefinition = "integer default 0", nullable = false)
	private Integer likeCount;
	
	@Column(columnDefinition = "integer default 0", nullable = false)
	private Integer dislikeCount;
	
	@Column
    private Long filesId;
	
	@Column(nullable = false)
	private String category;
	
	public SoccerPosts update(String title, String content, Long filesId) {
		this.title = title;
		this.content = content;
		this.filesId = filesId;
		
		return this;
	}
	
}