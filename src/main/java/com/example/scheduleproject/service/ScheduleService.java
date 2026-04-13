package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.CreateScheduleRequest;
import com.example.scheduleproject.dto.CreateScheduleResponse;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 전달받은 요청 데이터를 기반으로 {@link Schedule} 엔티티를 생성하고,
     * 저장 후 저장된 일정 정보를 {@link CreateScheduleResponse} 형태로 반환합니다.
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

}
