package com.example.scheduleproject.dto;

import lombok.Getter;

/**
 * 일정 수정 요청 데이터를 전달하는 DTO
 * 일정의 제목, 작성자명, 비밀번호 정보 포함
 */
@Getter
public class UpdateScheduleRequest {

    private String title;
    private String writer;
    private String password;

}
