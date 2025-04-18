package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private String userid;
    private String password;
    private boolean check;
}
