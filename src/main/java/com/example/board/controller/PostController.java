package com.example.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.dto.Post;
import com.example.board.service.PostService;

/**
 * 게시판 컨트롤러 - /Post/**
 */
@Controller
@RequestMapping("/Post")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	/** 게시글 목록 */
	@GetMapping("/list")
	public String list(Model model) {
		List<Post> postList = postService.list();
		model.addAttribute("postList", postList);
		return "Post/list";
	}

	/** 게시글 조회 - /Post/read?PostNo=1 */
	@GetMapping("/read")
	public String read(@RequestParam("PostNo") int postNo, Model model) {
		return readPost(postNo, model);
	}

	/** 게시글 조회 - /Post/read/1 */
	@GetMapping("/read/{postNo}")
	public String readByPath(@PathVariable("postNo") int postNo, Model model) {
		return readPost(postNo, model);
	}

	private String readPost(int postNo, Model model) {
		Post post = postService.select(postNo);
		if (post == null) {
			return "redirect:/Post/list";
		}
		model.addAttribute("post", post);
		return "Post/read";
	}

	/** 게시글 등록 화면 */
	@GetMapping("/insert")
	public String insertForm() {
		return "Post/insert";
	}

	/** 게시글 등록 처리 */
	@PostMapping("/insert")
	public String insert(@ModelAttribute Post post) {
		postService.insert(post);
		return "redirect:/Post/read?PostNo=" + post.getPostNo();
	}

	/** 게시글 수정 화면 */
	@GetMapping("/update")
	public String updateForm(@RequestParam("PostNo") int postNo, Model model) {
		Post post = postService.select(postNo);
		if (post == null) {
			return "redirect:/Post/list";
		}
		model.addAttribute("post", post);
		return "Post/update";
	}

	/** 게시글 수정 처리 */
	@PostMapping("/update")
	public String update(@ModelAttribute Post post) {
		postService.update(post);
		return "redirect:/Post/read?PostNo=" + post.getPostNo();
	}

	/** 게시글 삭제 처리 (AJAX) - DELETE /Post/delete/1 */
	@DeleteMapping("/delete/{postNo}")
	@ResponseBody
	public ResponseEntity<String> delete(@PathVariable("postNo") int postNo) {
		int result = postService.delete(postNo);
		if (result == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok("SUCCESS");
	}
}
