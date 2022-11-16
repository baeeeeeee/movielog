package com.movielog.dao;

public interface LikeDAO {
	public void updateLike(int bno) throws Exception;
	
	public void updateLikeCancel(int bno) throws Exception;
	
	public void insertLike(int bno,String userid) throws Exception;
	
	public void deleteLike(int bno,String userid)throws Exception;
	
	public int likeCheck(int bno,String userid) throws Exception;
	
	public void updateLikeCheck(int bno,String userid) throws Exception;
	
	public void updateLikeCheckCancel(int bno,String userid) throws Exception;

}
