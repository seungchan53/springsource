package com.example.novels.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "genre")
public class Novel {

    @Id
    @Column(name = "novel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private LocalDate publishedDate;

    @Column(nullable = false)
    private boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    public void changeAvailable(boolean available) {
        this.available = available;
    }

    public void changePublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

}
