package com.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 댓글 생성 응답 데이터를 전달하는 DTO
 * 댓글의 고유 식별자 번호, 내용, 작성자명, 일정 식별자 번호, 생성일, 수정일 정보 포함
 */
@Getter
public class CreateCommentResponse {

    private final Long id;
    private final String commentContent;
    private final String commentWriter;
    private final Long scheduleId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateCommentResponse(Long id, String commentContent, String commentWriter, Long scheduleId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.commentContent = commentContent;
        this.commentWriter = commentWriter;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
