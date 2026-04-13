> #  🗓️ Commerce Project 

> ## 👨‍🏫 프로젝트 소개

#### 이 프로젝트는 Spring Boot와 JPA 를 활용하여 일정 등록, 조회, 수정, 삭제 기능을 구현한 일정 관리 애플리케이션이다.
#### 3 Layer Architecture를 기반으로 Controller, Service, Repository의 역할을 분리하여 개발하였으며, MySQL과 연동하여 일정 데이터를 관리한다.

> ## 📐 설계
### 1. API 명세서
 - https://www.notion.so/CH3-API-3411cb3a8fa480e5ad3fdb946560a620?source=copy_link

### 2. ERD
 - 테이블 관계 이미지
 - 각 테이블 설명

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
