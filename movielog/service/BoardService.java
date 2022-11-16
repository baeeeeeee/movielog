package com.movielog.service;

import java.util.List;

import com.movielog.domain.BoardVO;
import com.movielog.domain.CateVO;
import com.movielog.domain.SearchCriteria;

public interface BoardService {

	/* 게시판 등록 */
	void create(BoardVO boardVO) throws Exception;

	/* 게시물 조회 */
	BoardVO read(Integer bno) throws Exception;

	/* 게시판 수정 */
	void update(BoardVO boardVO) throws Exception;

	/* 게시판 삭제 */
	void delete(Integer bno) throws Exception;

	/* 게시판 목록 */
	List<BoardVO> list(SearchCriteria searchCriteria) throws Exception;

	int countArticles(SearchCriteria searchCriteria) throws Exception;

	public List<CateVO> category() throws Exception;
	
	public List<BoardVO> notice() throws Exception;
	

}
