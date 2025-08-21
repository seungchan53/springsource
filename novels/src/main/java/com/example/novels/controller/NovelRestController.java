package com.example.novels.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.novels.dto.NovelDTO;
import com.example.novels.dto.PageRequestDTO;
import com.example.novels.dto.PageResultDTO;
import com.example.novels.entity.Novel;
import com.example.novels.service.NovelService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@RequestMapping("/api/books")
@RestController
@RequiredArgsConstructor
public class NovelRestController {

    @PutMapping("/{id}")
    public Long putNovel(@RequestBody NovelDTO novelDTO) {
        log.info("수정 {}", novelDTO);

        return novelService.avaUpdade(novelDTO);
    }

    @PutMapping("/edit/{id}")
    public Long putPubNovel(@RequestBody NovelDTO novelDTO) {
        log.info("출판일자 수정 {}", novelDTO);

        return novelService.pubUpdade(novelDTO);
    }

    @PostMapping("/add")
    public Long postMethodName(@RequestBody NovelDTO novelDTO) {
        log.info("추가 요청 {}", novelDTO);

        return novelService.novelInsert(novelDTO);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public Long removeNovel(@PathVariable Long id) {
        log.info("삭제 {}", id);
        novelService.novelRemove(id);
        return id;
    }

    private final NovelService novelService;

    @GetMapping("")
    public PageResultDTO<NovelDTO> getList(PageRequestDTO requestDTO) {
        log.info("전체 도서 정보 {}", requestDTO);
        PageResultDTO<NovelDTO> result = novelService.getList(requestDTO);
        return result;
    }

    @GetMapping("/{id}")
    public NovelDTO getRow(@PathVariable Long id) {
        log.info("도서 get {}", id);
        NovelDTO novelDTO = novelService.getRow(id);
        return novelDTO;
    }

}
