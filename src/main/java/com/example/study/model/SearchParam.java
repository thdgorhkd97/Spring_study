package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // getter, setter, 생성자 자동 생성
@AllArgsConstructor
public class SearchParam {

    private String account;
    private String email;
    private int page;


}
