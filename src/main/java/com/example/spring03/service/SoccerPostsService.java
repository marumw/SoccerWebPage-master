package com.example.spring03.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring03.domain.SoccerPosts;
import com.example.spring03.dto.SoccerPostsCreateDto;
import com.example.spring03.dto.SoccerPostsUpdateDto;
import com.example.spring03.repository.SoccerPostsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final 필드를 초기화하는 생성자.
@Service // 스프링 컨텍스트에 Service 컴포넌트로 등록.
public class SoccerPostsService {

	private final SoccerPostsRepository soccerPostsRepository;

	// 목록 보기
	public Page<SoccerPosts> read(Pageable pageable, String category) {
		log.info("read(category = {})", category);

		return soccerPostsRepository.findByCategoryIgnoreCaseContainingOrderByIdDesc(category, pageable);
	}
	
	public List<SoccerPosts> read() {
		log.info("read()");

		return soccerPostsRepository.ClickCountOrder();
	}

	// 새글 작성
	public SoccerPosts create(SoccerPostsCreateDto dto) {
		log.info("create(dto={})", dto);

		SoccerPosts entity = soccerPostsRepository.save(dto.toEntity());

		return entity;
	}

	// 특정 게시글 보기
	public SoccerPosts read(Integer id) {
		log.info("read(id={})", id);
		SoccerPosts post = soccerPostsRepository.findById(id).get();
		System.out.println(post);
		return post;
	}
	
	

	// 삭제
	public Integer delete(Integer id) {
		log.info("delete(id={})", id);

		soccerPostsRepository.deleteById(id);
		return id;
	}

	@Transactional
	public Integer update(SoccerPostsUpdateDto dto) {
		log.info("update(dto={})", dto);

		SoccerPosts entity = soccerPostsRepository.findById(dto.getId()).get();
		entity.update(dto.getTitle(), dto.getContent(), dto.getFilesId());

		return entity.getId();
	}

	public Page<SoccerPosts> search(String type, String keyword, Pageable pageable) {
		log.info("search(type = {}, keyword ={})", type, keyword);
		List<SoccerPosts> list = new ArrayList<>();

		Page<SoccerPosts> page = new PageImpl<>(list, pageable, list.size());
		switch (type) {
		case "t":
			page = soccerPostsRepository.findByTitleContaining(keyword, pageable);
			break;
		case "c":
			page = soccerPostsRepository.findByContentContaining(keyword, pageable);
			break;
		case "a":
			page = soccerPostsRepository.findByAuthorContaining(keyword, pageable);
			break;
		case "tc":
			page = soccerPostsRepository.searchByKeyword(keyword, pageable);
		}	

		return page;
	}
	
	@Transactional
	public void clickCount(Integer clickCount) {
		log.info("clickCount(조회수={})",clickCount);
		soccerPostsRepository.clickCount(clickCount);
	}
	
	// 좋아요 증가
	@Transactional
	public void likeCount(Integer likeCount) {
		log.info("likeCount(id={})", likeCount);
		soccerPostsRepository.likeCount(likeCount);
	}
		
	// 싫어요 증가
	@Transactional
	public void dislikeCount(Integer dislikeCount) {
		log.info("dislikeCount(id={})", dislikeCount);
		soccerPostsRepository.dislikeCount(dislikeCount);
	}
	
	// 작성자 별 게시글
	public List<SoccerPosts> readByAuthor(String author) {
        log.info("readByAuthor(author = {})", author);
        return soccerPostsRepository.findByAuthorContaining(author);
    }

}