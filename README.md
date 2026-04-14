> #  🗓️ Commerce Project 

> ## 👨‍🏫 프로젝트 소개

#### 이 프로젝트는 Spring Boot와 JPA 를 활용하여 일정 등록, 조회, 수정, 삭제 기능을 구현한 일정 관리 애플리케이션이다.
#### 3 Layer Architecture를 기반으로 Controller, Service, Repository의 역할을 분리하여 개발하였으며, MySQL과 연동하여 일정 데이터를 관리한다.

> ## 📐 설계
### 1. API 명세서
 - https://www.notion.so/CH3-API-3411cb3a8fa480e5ad3fdb946560a620?source=copy_link

### 2. ERD

### schedules 테이블

| 필드명 | 타입 | 설명 | 키 |
|--------|------|------|----|
| id | Long | 일정 식별자 | PK |
| title | String | 일정 제목 |  |
| contents | String | 일정 내용 |  |
| writer | String | 작성자명 |  |
| password | String | 비밀번호 |  |
| createdAt | LocalDateTime | 작성일 |  |
| modifiedAt | LocalDateTime | 수정일 |  |

---

### comments 테이블

| 필드명 | 타입 | 설명 | 키 |
|--------|------|------|----|
| id | Long | 댓글 식별자 | PK |
| scheduleId | Long | 일정 식별자 | FK |
| commentContent | String | 댓글 내용 |  |
| commentWriter | String | 댓글 작성자명 |  |
| commentPassword | String | 댓글 비밀번호 |  |
| createdAt | LocalDateTime | 작성일 |  |
| modifiedAt | LocalDateTime | 수정일 |  |

---

### 테이블 관계

| 부모 테이블 | 자식 테이블 | 관계 | 설명 |
|-------------|-------------|------|------|
| schedules | comments | 1 : N | 하나의 일정에 여러 개의 댓글이 작성될 수 있음 |


> ## 📌 주요기능

### LV 1. 일정 생성

- 일정 생성 기능 구현
  - 일정 제목, 내용, 작성자명, 비밀번호 저장
  - 작성일/수정일 자동 저장
  - 응답 시 비밀번호 제외

### LV 2. 일정 조회

- 전체 일정 조회 기능 구현
  - 작성자명을 기준으로 일정 목록 전부 조회(선택사항)
  - 수정일 기준 내림차순 정렬
  - 응답 시 비밀번호 제외
- 선택 일정 조회 기능 구현
  - 선택한 일정 단건의 정보 조회
  - 응답 시 비밀번호 제외

### LV 3. 일정 수정

- 선택한 일정 수정 기능 구현
  - 선택한 일정 내용 중 일정 제목, 작성자명만 수정 가능
  - 비밀번호 틀릴 경우 예외 발생
  - 응답 시 비밀번호 제외

### LV 4. 일정 삭제

- 선택한 일정 삭제
  - 비밀번호 틀릴 경우 예외 발생

### LV 5. 댓글 생성

- 댓글 생성 기능 구현
  - 댓글 내용, 작성자명, 비밀번호, 작성/수정일, 일정 고유식별자 저장
  - 하나의 일정에는 댓글 10개까지 등록
  - 응답 시 비밀번호 제외

### LV 6. 일정 단건 조회 업그레이드

- 일정 단건 조회 업그레이드
  - 조회 시, 해당 일정에 등록된 댓글들 포함하여 함께 응답
  - 응답 시 비밀번호 제외

### LV 7. 유저의 입력에 대한 검증 수행

- 일정 제목 : 최대 30자 이내 제한, 필수값 처리
- 일정 내용 : 최대 200자 이내 제한, 필수값 처리
- 댓글 내용 : 최대 100자 이내 제한, 필수값 처리
- 비밀번호, 작성자명 필수값 처리

> ## ⏲️ 개발기간
- 2026.04.13(월) ~ 2026.04.14(화)

> ## 📚️ 기술스택

### ✔️ Language
- Java 17

### ✔️ Framework
- Spring Boot
- Spring Web
- Spring Data JPA

### ✔️ Database
- MySQL

### ️️ ✔️ Library
- Lombok

### ✔️ IDE
- IntelliJ IDEA

### ✔️ Version Control
- Git
- GitHub

> ## 🔥 Trouble Shooting
### 1. PATCH 수정 시 수정일이 POSTMAN 응답에서 바로 반영되지 않는 문제

- 문제  
  일정 수정 API 호출 후 DB에는 `modifiedAt`가 변경이 되었지만,
  Postman 응답에서는 이전 수정일이 보이고 한 번 더 요청해야 최신 수정일이 보이는 문제 발생

- 원인
  JPA Auditing의` @LastModifiedDate`는 엔티티가 변경된 직후 즉시 보이는 것이 아니라,
  flush 혹은 commit 시점에 반영되는 특성이 있었다.
  따라서 응답 DTO를 너무 이른 시점에 생성하면 이전 값이 담길 수 있었다.

- 해결  
  수정 로직 이후 `flush()` 호출
  ```java
  scheduleRepository.flush();
  ```

### 2. 댓글 생성 요청에서 ScheduleId를 어디서 받아야 하는지 고민

- 문제  
  댓글 생성 시 `ScheduleId`를 통해 댓글 테이블과 일정 테이블을 연결하려고 하였으나,
  RequestBody 둘지, URL PathVariable로 받을지 고민하였다.

- 원인  
  처음에는 댓글 생성 요청 DTO 안에 scheduleId를 넣었지만,
  이미 URL에서 /schedules/{scheduleId}/comments 형태로 일정 id를 받고 있었기 때문에 중복 구조가 되었다.

- 해결  
  PathVariable로 받은 scheduleId를 기준으로 처리하고,
  Request DTO에서는 댓글 내용, 작성자명, 비밀번호만 받도록 수정하였다.
  Response DTO에는 필요한 경우 scheduleId를 포함할 수 있도록 정리하였다.

> #### Velog Url = https://velog.io/@gpekd5/TIL-Schedule-%EA%B3%BC%EC%A0%9C