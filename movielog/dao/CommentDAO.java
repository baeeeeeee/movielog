package com.movielog.dao;

import java.util.List;

import com.movielog.domain.CommentVO;
import com.movielog.domain.Criteria;

public interface CommentDAO {
	public List<CommentVO> list(Integer bno, Criteria cri) throws Exception;

	public void create(CommentVO vo) throws Exception;

	public void update(CommentVO vo) throws Exception;

	public void delete(Integer cno) throws Exception;

	public int count(Integer bno) throws Exception;
}
