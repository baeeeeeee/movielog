package com.movielog.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.movielog.dao.BoardDAO;
import com.movielog.domain.BoardVO;
import com.movielog.domain.CateVO;
import com.movielog.domain.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	@Override
	public void create(BoardVO boardVO) throws Exception {
		dao.create(boardVO);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		dao.viewcnt(bno);
		return dao.read(bno);
	}

	@Override
	public void update(BoardVO boardVO) throws Exception {
		dao.update(boardVO);
	}

	@Override
	public void delete(Integer bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> list(SearchCriteria searchCriteria) throws Exception {
		return dao.list(searchCriteria);
	}

	@Override
	public int countArticles(SearchCriteria searchCriteria) throws Exception {
		return dao.countArticles(searchCriteria);
	}

	@Override
	public List<CateVO> category() throws Exception {
		return dao.category();
	}
	
	@Override
	public List<BoardVO> notice() throws Exception{
		return dao.notice();
	}


}
