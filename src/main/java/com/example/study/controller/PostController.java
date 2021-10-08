package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // method끼리 주소가 같으면 실행 X, class끼리는 주소가 같아도 실행 가능
public class PostController {

    // HTML <Form>
    // ajax 에서 검색 등에서 주로 사용(주로 검색에 사용)
    // json, xml, multipart-form, text-plain 등

    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){

        return searchParam;
    }

}
