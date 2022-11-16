package com.movielog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.movielog.domain.BoardVO;
import com.movielog.domain.CateVO;
import com.movielog.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sql;

	private static String namespace = "com.movielog.mappers.board";

	@Override
	public void create(BoardVO boardVO) throws Exception {
		sql.insert(namespace + ".create", boardVO);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return sql.selectOne(namespace + ".read", bno);
	}

	@Override
	public void update(BoardVO boardVO) throws Exception {
		sql.update(namespace + ".update", boardVO);

	}

	@Override
	public void delete(Integer bno) throws Exception {
		sql.delete(namespace + ".delete", bno);
	}

	@Override
	public void viewcnt(Integer bno) throws Exception {
		sql.update(namespace + ".viewcnt", bno);

	}

	@Override
	public int countArticles(SearchCriteria searchCriteria) throws Exception {
		return sql.selectOne(namespace + ".countArticles", searchCriteria);
	}

	@Override
	public List<CateVO> category() throws Exception {
		return sql.selectList(namespace + ".category");
	}

	@Override
	public List<BoardVO> list(SearchCriteria searchCriteria) throws Exception {
		return sql.selectList(namespace + ".list", searchCriteria);
	}

	@Override
	public List<BoardVO> notice() throws Exception {
		return sql.selectList(namespace+".notice");
	}


}
