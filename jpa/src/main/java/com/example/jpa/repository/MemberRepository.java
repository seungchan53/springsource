package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.entity.Board;
import com.example.jpa.entity.Item;
import com.example.jpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
