package com.movielog.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movielog.dao.BoardDAO;
import com.movielog.dao.CommentDAO;
import com.movielog.domain.CommentVO;
import com.movielog.domain.Criteria;



@Service
public class CommentServiceImpl implements CommentService {
	 @Inject
	  private CommentDAO dao;

	@Override
	public List<CommentVO> list(Integer bno, Criteria cri) throws Exception {
		return dao.list(bno, cri);
	}

	@Override
	public void create(CommentVO vo) throws Exception {
		dao.create(vo);
		
	}

	@Override
	public void update(CommentVO vo) throws Exception {
		dao.update(vo);
		
	}

	@Override
	public void delete(Integer cno) throws Exception {
		dao.delete(cno);
		
	}

	@Override
	public int count(Integer bno) throws Exception {
		return dao.count(bno);
	}

	 


}