package com.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 일정 수정 요청 데이터를 전달하는 DTO
 * 일정의 고유 식별자, 제목, 내용, 작성자명, 생성일, 수정일 정보 포함
 */
@Getter
public class UpdateScheduleResponse {

    private final Long id;
    private final String title;
    private final String contents;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponse(Long id, String title, String contents, String writer, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
