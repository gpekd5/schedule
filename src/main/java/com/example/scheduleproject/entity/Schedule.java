package com.example.scheduleproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 일정 정보를 저장하는 엔티티 클래스
 */
@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 50, nullable = false)
    private String contents;
    @Column(length = 100, nullable = false)
    private String writer;
    @Column(length = 50, nullable = false)
    private String password;

    public Schedule(String title, String contents, String writer, String password) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.password = password;
    }

    public void update(String title, String writer) {
        if (title != null) {
            this.title = title;
        }

        if (writer != null) {
            this.writer = writer;
        }
    }
}
