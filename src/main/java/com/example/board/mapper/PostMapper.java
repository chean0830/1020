package com.example.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.board.dto.Page;
import com.example.board.dto.Post;

/**
 * 게시글 매퍼 - SQL은 resources/mapper/PostMapper.xml 에 작성
 */
@Mapper
public interface PostMapper {

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
