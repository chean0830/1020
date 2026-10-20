package com.example.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.dto.Page;
import com.example.board.dto.Post;
import com.example.board.mapper.PostMapper;

/**
 * 게시글 서비스 구현
 */
@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

	private final PostMapper postMapper;

	// 생성자 주입
	public PostServiceImpl(PostMapper postMapper) {
		this.postMapper = postMapper;
	}

	@Override
	public int count() {
		return postMapper.count();
	}

	@Override
	public List<Post> list(Page page) {
		return postMapper.list(page);
	}

	@Override
	public Post select(int postNo) {
		return postMapper.select(postNo);
	}

	@Override
	@Transactional
	public int insert(Post post) {
		return postMapper.insert(post);
	}

	@Override
	@Transactional
	public int update(Post post) {
		return postMapper.update(post);
	}

	@Override
	@Transactional
	public int delete(int postNo) {
		return postMapper.delete(postNo);
	}
}
