package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.CreateCommentRequest;
import com.example.scheduleproject.dto.CreateCommentResponse;
import com.example.scheduleproject.dto.CreateScheduleRequest;
import com.example.scheduleproject.dto.CreateScheduleResponse;
import com.example.scheduleproject.entity.Comment;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.repository.CommentRepository;
import com.example.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 댓글 관련 비즈니스 로직을 처리하는 서비스 클래스
 *
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    /**
     * 새로운 일정 저장
     * 요청 DTO 기반으로 Schedule 엔티티 생성하고 DB 저장 후
     * 저장된 데이터를 응답 DTO로 변환하여 반환
     *
     * @param scheduleId 댓글 생성에 필요한 일정 고유 식별자
     * @param request 댓글 생성에 필요한 댓글 내용, 작성자명, 비밀번호를 담은 Request DTO
     * @return 저장된 댓글의 고유 식별자, 댓글 내용, 작성자명, 일정 고유 식별자, 생성일, 수정일 정보를 담은 Response DTO
     */
    @Transactional
    public CreateCommentResponse save(Long scheduleId,CreateCommentRequest request) {
        VaildateComment(request);

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        long count = commentRepository.countCommentByScheduleId(scheduleId);
        if (count >= 10) {
            throw new IllegalStateException("댓글은 최대 10개까지만 작성할 수 있습니다.");
        }

        Comment comment = new Comment(
                request.getCommentContent(),
                request.getCommentWriter(),
                request.getCommentPassword(),
                schedule.getId()
        );

        Comment saveComment = commentRepository.save(comment);

        return new CreateCommentResponse(
                saveComment.getId(),
                saveComment.getCommentContent(),
                saveComment.getCommentWriter(),
                saveComment.getScheduleId(),
                saveComment.getCreatedAt(),
                saveComment.getModifiedAt()
        );

    }

    /**
     * 댓글 정보 유효성 검증
     * @param request 검증할 DTO
     */
    public void VaildateComment(CreateCommentRequest request) {
        if (request.getCommentContent() == null || request.getCommentContent().isBlank()) {
            throw new IllegalStateException("댓글 내용은 필수입니다.");
        }
        if (request.getCommentContent().length() > 100) {
            throw new IllegalStateException("댓글 내용은 최대 100자 이내입니다.");
        }
        if (request.getCommentPassword() == null || request.getCommentPassword().isBlank()) {
            throw new IllegalStateException("댓글 비밀번호는 필수입니다.");
        }
        if (request.getCommentWriter() == null || request.getCommentWriter().isBlank()) {
            throw new IllegalStateException("댓글 작성자는 필수입니다.");
        }
    }

}
