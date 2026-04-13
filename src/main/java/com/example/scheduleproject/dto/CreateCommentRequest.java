package com.example.scheduleproject.dto;

import lombok.Getter;

/**
 * 댓글 생성 요청 데이터를 전달하는 DTO
 * 댓글 내용, 작성자명, 비밀번호 정보 포함
 */
@Getter
public class CreateCommentRequest {

    private String commentContent;
    private String commentWriter;
    private String commentPassword;
}
