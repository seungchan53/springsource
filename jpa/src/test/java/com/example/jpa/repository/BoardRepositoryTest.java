package com.example.jpa.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    // CRUD
    @Test
    public void insertTest() {
        LongStream.rangeClosed(1, 10).forEach(i -> {

            Board board = Board.builder()
                    .title("title test" + i)
                    .content("content" + i)
                    .writer("writer test" + i)
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    public void updateTest() {

        Board board = boardRepository.findById(1L).get();
        board.setTitle("titleText update");
        boardRepository.save(board);

    }

    @Test
    public void readTest() {
        Board board = boardRepository.findById(1L).get();
        System.out.println(board);
    }

    @Test
    public void listTest() {
        boardRepository.findAll().forEach(board -> System.out.println(board));

    }

    @Test
    public void deleteTest() {
        boardRepository.deleteById(10L);

    }
}
