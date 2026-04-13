package com.example.scheduleproject.dto;

import lombok.Getter;

/**
 * 일정 생성 요청 데이터를 전달하는 DTO
 * 일정의 제목, 내용, 작성자명, 비밀번호 정보 포함
 */
@Getter
public class CreateScheduleRequest {

    private String title;
    private String contents;
    private String writer;
    private String password;
}
