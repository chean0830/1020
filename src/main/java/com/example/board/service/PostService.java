package com.example.board.service;

import java.util.List;

import com.example.board.dto.Page;
import com.example.board.dto.Post;

/**
 * 게시글 서비스
 */
public interface PostService {

	// 전체 게시글 수 (페이징)
	int count();

	// 게시글 목록 (페이징)
	List<Post> list(Page page);

	// 게시글 조회
	Post select(int postNo);

	// 게시글 등록
	int insert(Post post);

	// 게시글 수정
	int update(Post post);

	// 게시글 삭제
	int delete(int postNo);
}
