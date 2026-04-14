package com.example.scheduleproject.repository;

import com.example.scheduleproject.dto.GetCommentResponse;
import com.example.scheduleproject.entity.Comment;
import com.example.scheduleproject.entity.Schedule;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 댓글 관련 데이터베이스 상호작용 하는 레포지토리 클래스
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Long countCommentByScheduleId(Long scheduleId);
    List<Comment> findByScheduleId(Long scheduleId);
}
