package com.example.board.dto;

import java.time.LocalDateTime;

/**
 * 게시글 DTO (post 테이블 한 행)
 */
public class Post {

	private int postNo;              // 글번호
	private String title;            // 제목
	private String writer;           // 작성자
	private String content;          // 내용
	private LocalDateTime regDate;   // 등록일
	private LocalDateTime updDate;   // 수정일

	public Post() {
	}

	public Post(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public LocalDateTime getUpdDate() {
		return updDate;
	}

	public void setUpdDate(LocalDateTime updDate) {
		this.updDate = updDate;
	}

	@Override
	public String toString() {
		return "Post{postNo=" + postNo + ", title='" + title + "', writer='" + writer
				+ "', regDate=" + regDate + ", updDate=" + updDate + "}";
	}
}
