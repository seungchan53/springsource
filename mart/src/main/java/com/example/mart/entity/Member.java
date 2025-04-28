package com.example.mart.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

@Table(name = "MART_MEMBER")
@Entity
public class Member extends BaseEntity {
    // 회원(회원번호(pk),이름,우편번호,주소,상세주소)이 있다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;

    private String name;

    private String zipcode;
    private String city;
    private String street;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
