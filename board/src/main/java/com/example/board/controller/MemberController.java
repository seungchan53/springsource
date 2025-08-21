package com.example.board.controller;

import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.security.AuthMemberDTO;
import com.example.board.security.ClubMemberDetailsService;
import com.example.board.security.MemberDTO;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final ClubMemberDetailsService service;

    // private final

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public void getAdmin() {
        log.info("admin 폼 요청");
    }

    @GetMapping("/login")
    public void getLogin() {
    }

    @GetMapping("/register")
    public void getRegister(MemberDTO dto) {

    }

    @PostMapping("/register")
    public String postRegister(@Valid MemberDTO dto, BindingResult result, Model model) {
        log.info("회원 가입 요청 {}", dto);

        if (result.hasErrors()) {
            return "member/register";
        }
        try {
            service.register(dto);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "member/register";

        }

        return "redirect:/member/login";
    }

    @ResponseBody
    @GetMapping("/auth")
    public Authentication gAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        return authentication;
    }

}
