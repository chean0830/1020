# 1020 — Spring Boot MVC 게시판

Spring Boot · MyBatis · Thymeleaf · MySQL 로 만든 게시판입니다.
게시글 **등록 · 목록(페이징) · 조회 · 수정 · 삭제(AJAX)** 를 구현했습니다.

## 개발 환경

| 구분 | 내용 |
| --- | --- |
| Framework | Spring Boot 3.5.16 (Gradle) |
| Persistence | MyBatis 3.0 (mybatis-spring-boot-starter 3.0.4) |
| View | Thymeleaf |
| DB | MySQL 8 |
| Java | 17 이상 |

## 기능과 URL

| 기능 | URL | 화면 |
| --- | --- | --- |
| 메인 | `GET /` | `templates/index.html` |
| 목록 (페이징) | `GET /Post/list?page=1` | `templates/Post/list.html` |
| 조회 | `GET /Post/read?PostNo=1` 또는 `GET /Post/read/1` | `templates/Post/read.html` |
| 등록 | `GET /Post/insert` → `POST /Post/insert` | `templates/Post/insert.html` |
| 수정 | `GET /Post/update?PostNo=1` → `POST /Post/update` | `templates/Post/update.html` |
| 삭제 | `DELETE /Post/delete/1` (수정 화면의 [삭제] 버튼에서 AJAX로 호출) | — |

- 페이징: 한 페이지 10개, 페이지 번호 5개씩 표시 (`[처음] [이전] 1 2 3 4 5 [다음] [끝]`)

## 구조

```
src/main/java/com/example/board
├── controller/PostController.java      # /Post/** 요청 처리
├── dto/Post.java                        # 게시글
├── dto/Page.java                        # 페이징 정보 (offset · 시작/끝 페이지 계산)
├── mapper/PostMapper.java               # MyBatis 매퍼 인터페이스
└── service/PostService.java, PostServiceImpl.java
src/main/resources
├── mapper/PostMapper.xml                # SQL (등록 · 목록 · 개수 · 조회 · 수정 · 삭제)
├── schema.sql                           # post 테이블 (서버 시작 시 자동 생성)
├── templates/index.html, templates/Post/*.html
└── application.properties
```

## 테이블

```sql
CREATE TABLE IF NOT EXISTS post (
    post_no  INT          NOT NULL AUTO_INCREMENT,  -- 글번호
    title    VARCHAR(200) NOT NULL,                 -- 제목
    writer   VARCHAR(50)  NOT NULL,                 -- 작성자
    content  TEXT         NOT NULL,                 -- 내용
    reg_date DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,                              -- 등록일
    upd_date DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 수정일
    PRIMARY KEY (post_no)
);
```

## 실행 방법

1. MySQL에 접속 계정을 만듭니다. (DB와 테이블은 서버 시작 시 자동 생성)
   ```sql
   CREATE USER 'board'@'localhost' IDENTIFIED BY '비밀번호';
   GRANT ALL PRIVILEGES ON post_board.* TO 'board'@'localhost';
   ```
   다른 계정을 쓰려면 `application.properties`의 `spring.datasource.username/password`를 바꿉니다.
2. 실행
   ```bash
   ./gradlew bootRun
   ```
3. 브라우저에서 http://localhost:8080 접속
