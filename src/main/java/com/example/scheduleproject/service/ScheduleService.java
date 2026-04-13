package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.*;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 일정 관련 비즈니스 로직을 처리하는 서비스 클래스
 *
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    /**
     * 새로운 일정 저장
     * 요청 DTO 기반으로 Schedule 엔티티 생성하고 DB 저장 후
     * 저장된 데이터를 응답 DTO로 변환하여 반환
     *
     * @param request 일정 생성에 필요한 일정 제목, 일정 내용, 작성자명, 비밀번호를 담은 Request DTO
     * @return 저장된 일정의 고유 식별자, 일정 제목, 일정 내용, 작성자명, 생성일, 수정일 정보를 담은 Response DTO
     */
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContents(),
                request.getWriter(),
                request.getPassword());

        Schedule saveSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                saveSchedule.getId(),
                saveSchedule.getTitle(),
                saveSchedule.getContents(),
                saveSchedule.getWriter(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt()
        );
    }

    /**
     * 전체 일정 조회
     * 작성자명이 없으면 전체 일정을 수정일 기준 내림차순으로 조회
     * 작성자명이 있으면 해당 작성자의 일정만 수정일 기준 내림차순으로 조회
     *
     * @param writer 조회할 작성자명
     * @return 일정 목록 응답 DTO 리스트
     */
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll(String writer) {
        List<Schedule> schedules;

        if (writer == null || writer.isBlank()) {
            schedules = scheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "modifiedAt"));
        } else {
           schedules = scheduleRepository.findByWriter(writer, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        }
        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getWriter(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );

            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * 일정 단건 조회
     * scheduleId에 해당하는 일정이 없으면 예외 발생
     *
     * @param scheduleId 조회할 일정의 ID
     * @return 조회된 일정 정보를 담은 응답 DTO
     */
    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    /**
     * 일정 수정
     * scheduleId에 해당하는 일정이 없으면 예외 발생
     * 비밀번호가 일치하지 않으면 예외 발생
     *
     * @param scheduleId
     * @param request
     * @return
     */
    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        if (schedule.getPassword().equals(request.getPassword()))
        {
            schedule.update(request.getTitle(), request.getWriter());

            scheduleRepository.flush();

            return new UpdateScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getWriter(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
        } else {
            throw new IllegalStateException("비밀번호가 다릅니다.");
        }
    }
}
