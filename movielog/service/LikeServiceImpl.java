package com.movielog.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.movielog.dao.LikeDAO;
import com.movielog.dao.MemberDAO;

@Service
public class LikeServiceImpl implements LikeService {
	
	@Inject
	private LikeDAO dao;

	
	@Override
	public void updateLike(int bno) throws Exception{
		 dao.updateLike(bno);
	}
	
	@Override
	public void updateLikeCancel(int bno) throws Exception{
		dao.updateLikeCancel(bno);
	}

	
	@Override
	public void insertLike(int bno,String userid) throws Exception{
			dao.insertLike(bno, userid);
	}
	
	@Override
	public void deleteLike(int bno,String userid)throws Exception{
			dao.deleteLike(bno, userid);
	}
	
	@Override
	public int likeCheck(int bno,String userid) throws Exception{
		return dao.likeCheck(bno, userid);
	}
	
	@Override
	public void updateLikeCheck(int bno,String userid)throws Exception{
		dao.updateLikeCheck(bno, userid);
	}
	
	@Override
	public void updateLikeCheckCancel(int bno,String userid)throws Exception{
		dao.updateLikeCheckCancel(bno, userid);
	}
	


}
