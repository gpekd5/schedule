package com.example.scheduleproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 댓글 정보를 저장하는 엔티티 클래스
 */
@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentContent;
    private String commentWriter;
    private String commentPassword;
    private Long scheduleId;

    public Comment(String commentContent, String commentWriter, String commentPassword, Long scheduleId) {
        this.commentContent = commentContent;
        this.commentWriter = commentWriter;
        this.commentPassword = commentPassword;
        this.scheduleId = scheduleId;
    }
}
