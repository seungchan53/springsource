package com.example.jpa.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.jpa.dto.MemoDTO;
import com.example.jpa.service.MemoService;

@Log4j2
@RequestMapping("/memo")
@RequiredArgsConstructor
@Controller
public class MemoController {
    // 서비스 메소드 호출
    // 데이터가 전송된다면 전송된 데이터를 model 에 담기
    private final MemoService memoService;

    // 주소 설계
    // 전체 memo 조회 : /memo/list
    @GetMapping("/list")
    public void getList(Model model) {
        List<MemoDTO> list = memoService.getList();
        model.addAttribute("list", list);

    }

    // 특정 memo 조회 : /memo/read?mno=3
    @GetMapping("/read")
    public void getRow() {

    }

    // 특정 memo 수정 : /memo/update?mno=3
    // memo 추가 : /memo/new
    @GetMapping("/new")
    public void getNew() {

    }

    // 메모 삭제 : /memo/remove?mno=3
    // @GetMapping("/delete")
    // public void getMethodName() {

    // }

}
