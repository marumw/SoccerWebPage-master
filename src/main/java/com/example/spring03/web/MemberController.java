package com.example.spring03.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring03.domain.Member;
import com.example.spring03.domain.SoccerPosts;
import com.example.spring03.dto.MailDto;
import com.example.spring03.dto.MemberRegisterDto;
import com.example.spring03.dto.MemberUpdateDto;
import com.example.spring03.service.MailService;
import com.example.spring03.service.MemberService;
import com.example.spring03.service.SoccerPostsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    
    private final MemberService memberService;
    private final MailService mailService;
    private final SoccerPostsService soccerPostsService;
    
    @GetMapping("/login")
    public String loginPage() {
        log.info("login()");
        
        return "/member/login";
        
    }
    
    @GetMapping("/register")
    public void registerPage() {
        log.info("register() GET");
    }
    
    

    @PostMapping("/register")
    public String register(MemberRegisterDto dto, MailDto mdto) {
        log.info("signUp(dto={}, mdto = {})",dto, mdto);
        
        memberService.registerMember(dto);
        
        mdto.setTitle("축사 회원가입을 축하합니다.");
        mdto.setMessage(dto.getUsername() + "님의 회원가입을 축하드립니다.");
        mailService.justSend(mdto);
        
        return "redirect:/member/login";
    }
    
    @GetMapping("/checkid")
    @ResponseBody
    public ResponseEntity<String> checkUsername(String username) {
        log.info("checkUsername(username={})", username);
        
        String result = memberService.checkUsername(username);
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/list") // 요청 URL/방식 매핑.
    public String read(Model model, @PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
        log.info("read()");
        
        Page<Member> list = memberService.read(pageable); // DB에서 포스트 목록 전체 검색.
        
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());
        
        model.addAttribute("list", list); // 뷰에 전달하는 모델 데이터.
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        
        return "/member/list";
        // View 이름 -> src/main/resources/templates/post/list.html
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public void profilePage(Principal principal,Model model) {
        
        log.info("profile() GET");
        String user=principal.getName();
        Member member= memberService.read(user);
        model.addAttribute("member", member);
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modifyPassword")
    public void modifyPage(Principal principal,Model model) {
        
        log.info("profile() GET");
        String user=principal.getName();
        Member member= memberService.read(user);
        log.info("member={}", member);
        model.addAttribute("member", member);
    }
    
    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        log.info("forgotPassword()");
        
        return "/member/forgotPassword";
        
    }
    
    @GetMapping({ "/detail", "/modify" })
    public void detail(Integer id, Model model) {
        log.info("detail(id={})", id);
        log.info("modify(id={})", id);

        Member member = memberService.readByMember(id);
        List<SoccerPosts> list = soccerPostsService.readByAuthor(member.getUsername());
        
        model.addAttribute("member", member);
        model.addAttribute("list", list);
    }
    
    @PostMapping("/update")
    public String update(MemberUpdateDto dto, RedirectAttributes attrs) {
        log.info("update(dto={}", dto);
        
        Integer userId = memberService.update(dto);
        attrs.addFlashAttribute("updatedId", userId);
        
        return "redirect:/member/detail?id=" + userId;       
    }
    
    @PostMapping("/delete")
    public String delete(Integer id, RedirectAttributes attrs, HttpSession session) {
        log.info("delete(id = {})", id);
        
        String sessionId = (String)session.getAttribute("id");
        log.info(sessionId);
        
        session.removeAttribute(sessionId);
        memberService.delete(id);
        
        attrs.addFlashAttribute("deletedId", id);

        return "redirect:/";
    }
    

}