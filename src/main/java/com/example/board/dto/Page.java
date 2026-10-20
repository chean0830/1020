package com.example.board.dto;

/**
 * 페이징 정보
 * - page      : 현재 페이지 번호 (1부터)
 * - size      : 한 페이지에 보여줄 게시글 수
 * - blockSize : 페이지 번호를 한 번에 몇 개씩 보여줄지 (예: 1 2 3 4 5)
 */
public class Page {

	private int page = 1;          // 현재 페이지
	private int size = 10;         // 페이지당 게시글 수
	private int blockSize = 5;     // 한 번에 보여줄 페이지 번호 수
	private int totalCount;        // 전체 게시글 수

	private int totalPages;        // 전체 페이지 수
	private int startPage;         // 현재 블록의 시작 페이지
	private int endPage;           // 현재 블록의 끝 페이지
	private int offset;            // SQL LIMIT 시작 위치

	public Page() {
	}

	public Page(int page, int size, int totalCount) {
		this.size = size;
		this.totalCount = totalCount;
		this.page = page;
		calculate();
	}

	/** 전체 게시글 수와 현재 페이지로 나머지 값을 계산한다. */
	private void calculate() {
		totalPages = Math.max(1, (int) Math.ceil((double) totalCount / size));
		// 범위를 벗어난 페이지 번호는 1 ~ 마지막 페이지로 맞춘다.
		page = Math.min(Math.max(page, 1), totalPages);

		offset = (page - 1) * size;

		startPage = ((page - 1) / blockSize) * blockSize + 1;
		endPage = Math.min(startPage + blockSize - 1, totalPages);
	}

	/** 이전 블록이 있는지 */
	public boolean isPrev() {
		return startPage > 1;
	}

	/** 다음 블록이 있는지 */
	public boolean isNext() {
		return endPage < totalPages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getOffset() {
		return offset;
	}

	@Override
	public String toString() {
		return "Page{page=" + page + ", size=" + size + ", totalCount=" + totalCount + ", totalPages=" + totalPages
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", offset=" + offset + "}";
	}
}
