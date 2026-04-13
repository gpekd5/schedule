package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.*;
import com.example.scheduleproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정 관련 HTTP 요청을 처리하는 컨트롤러
 * 일정 생성, 전체 조회, 단건 조회, 수정, 삭제 API 제공
 */
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 새로운 일정 생성
     *
     * @param request 일정 생성에 필요한 정보를 담은 요청 DTO
     * @return 생성된 일정 정보를 담은 응답 DTO와 HTTP 201 상태 코드
     */
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    /**
     * 전체 일정 조회
     *
     * @param writer 작성자명 조회 조건
     * @return 일정 목록 응답 DTO와 HTTP 200 상태 코드
     */
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedule(@RequestParam(required = false) String writer) {
        return  ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(writer));
    }

    /**
     * 단건 일정 조회
     * 고유 식별자를 기준으로 단건 일정 조회
     *
     * @param scheduleId 조회할 일정의 고유 식별자
     * @return 일정 목록 응답 DTO와 HTTP 200 상태 코드
     */
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOneSchedule(@PathVariable Long scheduleId) {
        return  ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }

    /**
     * 선택한 일정 수정
     * 고유 식별자를 기준으로 일정 수정
     *
     * @param scheduleId 조회할 일정의 고유 식별자
     * @return 일정 목록 응답 DTO와 HTTP 200 상태 코드
     */
    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> UpdateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
            ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }
}
