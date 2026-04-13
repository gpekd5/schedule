package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.CreateCommentRequest;
import com.example.scheduleproject.dto.CreateCommentResponse;
import com.example.scheduleproject.dto.CreateScheduleRequest;
import com.example.scheduleproject.dto.CreateScheduleResponse;
import com.example.scheduleproject.service.CommentService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 댓글 관련 HTTP 요청을 처리하는 컨트롤러
 * 댓글 생성 API 제공
 */
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 새로운 댓글 생성
     *
     * @param schedulesId 댓글 생성에 필요한 일정 고유 식별자
     * @param request 댓글 생성에 필요한 정보를 담은 요청 DTO
     * @return 생성된 댓글 정보를 담은 응답 DTO와 HTTP 201 상태 코드
     */
    @PostMapping("/schedules/{schedulesId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long schedulesId,
            @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(schedulesId, request));
    }


}
