package com.example.todo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ToDoDTO {
    private Long id;

    private String content;

    private boolean completed;

    private Long importanted;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
