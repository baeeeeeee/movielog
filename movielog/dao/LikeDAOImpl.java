package com.movielog.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class LikeDAOImpl implements LikeDAO {
	
	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.movielog.mappers.like";

	@Override
	public void updateLike(int bno) throws Exception{
		sql.update(namespace+ ".updateLike", bno);
	}
	
	@Override
	public void updateLikeCancel(int bno) throws Exception{
		sql.update(namespace+ ".updateLikeCancel", bno);

	}

	@Override
	public void insertLike(int bno,String userid) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("bno", bno);
		sql.insert(namespace+".insertLike", map);
	}
	
	@Override
	public void deleteLike(int bno,String userid)throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("bno", bno);
		sql.delete(namespace+".deleteLike", map);
	}
	
	@Override
	public int likeCheck(int bno,String userid) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("bno", bno);
		return sql.selectOne(namespace+".likeCheck", map);
	}
	
	@Override
	public void updateLikeCheck(int bno,String userid) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("bno", bno);
		sql.update(namespace+".updateLikeCheck", map);
		
	}
			
	@Override
	public void updateLikeCheckCancel(int bno,String userid) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("bno", bno);
		sql.update(namespace+".updateLikeCheckCancel", map);
	}
	


}
