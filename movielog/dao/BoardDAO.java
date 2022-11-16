package com.movielog.dao;

import java.util.List;

import com.movielog.domain.BoardVO;
import com.movielog.domain.CateVO;
import com.movielog.domain.SearchCriteria;

public interface BoardDAO {

	List<BoardVO> list(SearchCriteria searchCriteria) throws Exception;

	void create(BoardVO boardVO) throws Exception;

	BoardVO read(Integer bno) throws Exception;

	void update(BoardVO boardVO) throws Exception;

	void delete(Integer bno) throws Exception;

	void viewcnt(Integer bno) throws Exception;

	int countArticles(SearchCriteria searchCriteria) throws Exception;

	public List<CateVO> category() throws Exception;
	
	public List<BoardVO> notice() throws Exception;
	


}
