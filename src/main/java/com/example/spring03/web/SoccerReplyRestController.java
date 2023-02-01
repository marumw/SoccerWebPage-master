package com.example.spring03.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring03.dto.SoccerReplyReadDto;
import com.example.spring03.dto.SoccerReplyResiterDto;
import com.example.spring03.dto.SoccerReplyUpdateDto;
import com.example.spring03.service.SoccerReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController // REST 서비스에서 사용되는 컨트롤러 객체
// -> @RestController 클래스의 메서드 리턴값 => 클라이언트로 전송되는 값(response.data)!
@RequestMapping("/api/reply")
public class SoccerReplyRestController {
	
	private final SoccerReplyService soccerReplyService;
	
	@PostMapping // 댓글 등록 REST 서비스
	public ResponseEntity<Integer> registerReply(@RequestBody SoccerReplyResiterDto dto) {
		log.info("registerReply(dto = {})", dto);
		
		// 댓글 데이터베이스 테이블에 등록.
		Integer replyId = soccerReplyService.create(dto);
		
		// 작성된 댓글의 아이디를 응답 데이터(response data)로 만들어서 Ajax 요청에 대한 성공 응답.
		return ResponseEntity.ok(replyId); 
	}
	
	@GetMapping("/all/{postId}")
	// 요청 주소에서 변수에 해당하는 부분을 {}로 묶어주고 메서드 파라미터에서 @PathVariable을 선언하고 argument로 같은 값을 작성.
	// REST controller에서 ResponseEntity를 리턴할 때 데이터 타입으로 엔터티 클래스를 사용하면 안됨.
	// ResponseEntity는 직렬화가 가능한 클래스만 가능한데 엔터티 클래스는 직렬화가 가능할지 아닐지 모름. 직렬화를 시킬수는 있지만
	// DB와 연결되는 엔터티 클래스는 직렬화를 하지 않음.
	public ResponseEntity<List<SoccerReplyReadDto>> readAllReplies(@PathVariable Integer postId){
		// @PathVariable: 요청 주소에서 변수처럼 값이 변하는 부분
		log.info("readAllReplies(postId = {})", postId);
		
		List<SoccerReplyReadDto> list = soccerReplyService.readReplies(postId);
		log.info("# of list = {}", list.size());
		
		return ResponseEntity.ok(list);
		// Java의 List 타입 객체가 JSON(JavaScript Object Notation)문자열로 변환되서 클라이언트(브라우저)로 전송.
	}
	
	@GetMapping("/{replyId}") 
	public ResponseEntity<SoccerReplyReadDto> getReply(@PathVariable Integer replyId){
		log.info("getReply(replyId = {})", replyId);
		
		SoccerReplyReadDto dto = soccerReplyService.readReply(replyId);
		
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/{replyId}")
	public ResponseEntity<Integer> deleteReply(@PathVariable Integer replyId){
		log.info("deleteReply(replyId = {})", replyId);
		
		Integer result = soccerReplyService.delete(replyId);
		
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/{replyId}")
	public ResponseEntity<Integer> updateReply(@PathVariable Integer replyId, @RequestBody SoccerReplyUpdateDto dto){
		log.info("updateReply(replyId = {}, dto = {})", replyId, dto);
		
		dto.setReplyId(replyId); // DTO에 댓글 아이디를 저장.
		Integer result = soccerReplyService.update(dto);
		
		return ResponseEntity.ok(result);
	}

}
