package com.example.novels.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.novels.dto.NovelDTO;
import com.example.novels.dto.PageRequestDTO;
import com.example.novels.dto.PageResultDTO;
import com.example.novels.entity.Genre;
import com.example.novels.entity.Novel;
import com.example.novels.repository.GradeRepository;
import com.example.novels.repository.NovelRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class NovelService {

    private final NovelRepository novelRepository;

    private final GradeRepository gradeRepository;

    public Long avaUpdade(NovelDTO novelDTO) {

        Novel novel = novelRepository.findById(novelDTO.getId()).get();
        novel.changeAvailable(novelDTO.isAvailable());
        return novelRepository.save(novel).getId();
    }

    public Long pubUpdade(NovelDTO novelDTO) {
        // publishedDate 변경
        Novel novel = novelRepository.findById(novelDTO.getId()).get();
        novel.changePublishedDate(novelDTO.getPublishedDate());
        return novelRepository.save(novel).getId();
    }

    public void novelRemove(Long id) {
        // novel 삭제
        // 자식에 해당하는 grade 삭제
        gradeRepository.deleteByNovel(Novel.builder().id(id).build());
        // novel 삭제
        novelRepository.deleteById(id);

    }

    public Long novelInsert(NovelDTO novelDTO) {
        // novel 추가

        Novel novel = Novel.builder()
                .title(novelDTO.getTitle())
                .author(novelDTO.getAuthor())
                .publishedDate(novelDTO.getPublishedDate())
                .available(novelDTO.isAvailable())
                .genre(Genre.builder().id(novelDTO.getGid()).build())
                .build();
        return novelRepository.save(novel).getId();

    }

    public PageResultDTO<NovelDTO> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
                Sort.by("id").descending());
        Page<Object[]> result = novelRepository.list(pageable);

        // entity => dto
        List<NovelDTO> dtoList = result.get().map(arr -> {
            Novel novel = (Novel) arr[0];
            Genre genre = (Genre) arr[1];
            Double rating = (Double) arr[2];

            NovelDTO novelDTO = NovelDTO.builder()
                    .id(novel.getId())
                    .title(novel.getTitle())
                    .author(novel.getAuthor())
                    .publishedDate(novel.getPublishedDate())
                    .available(novel.isAvailable())
                    .gid(genre.getId())
                    .genreName(genre.getName())
                    .rating(rating != null ? rating.intValue() : 0)
                    .build();

            return novelDTO;
        }).collect(Collectors.toList());

        long totalCount = result.getTotalElements();
        return PageResultDTO.<NovelDTO>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    public NovelDTO getRow(Long id) {
        Object[] result = novelRepository.getNovelById(id);

        Novel novel = (Novel) result[0];
        Genre genre = (Genre) result[1];
        Double rating = (Double) result[2];
        NovelDTO dto = entityToDto(novel, genre, rating);
        return dto;
    }

    private NovelDTO entityToDto(Novel novel, Genre genre, Double rating) {
        NovelDTO novelDTO = NovelDTO.builder()
                .id(novel.getId())
                .title(novel.getTitle())
                .author(novel.getAuthor())
                .publishedDate(novel.getPublishedDate())
                .available(novel.isAvailable())
                .gid(genre.getId())
                .genreName(genre.getName())
                .rating(rating != null ? rating.intValue() : 0)
                .build();
        return novelDTO;
    }
}