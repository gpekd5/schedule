package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.CreateScheduleRequest;
import com.example.scheduleproject.dto.CreateScheduleResponse;
import com.example.scheduleproject.dto.GetScheduleResponse;
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
     * 새로운 일정을 생성합니다.
     *
     * @param request 일정 생성에 필요한 정보를 담은 요청 DTO
     * @return 생성된 일정 정보를 담은 응답 DTO와 HTTP 201 상태 코드
     */
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    /**
     * 전체 일정을 조회합니다.
     * 작성자명이 전달되면 해당 작성자의 일정만 조회하고,
     * 작성자명이 없으면 전체 일정을 조회합니다.
     *
     * @param writer 작성자명 조회 조건
     * @return 일정 목록 응답 DTO와 HTTP 200 상태 코드
     */
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedule(@RequestParam(required = false) String writer) {
        return  ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(writer));
    }

    /**
     * 전체 일정을 조회합니다.
     * 고유 식별자를 기준으로 단건 일정 조회
     *
     * @param scheduleId 조회할 일정의 고유 식별자
     * @return 일정 목록 응답 DTO와 HTTP 200 상태 코드
     */
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOneSchedule(@PathVariable Long scheduleId) {
        return  ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }
}
