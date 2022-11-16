package com.movielog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;

import com.movielog.domain.CommentVO;
import com.movielog.domain.Criteria;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Inject
	private SqlSession sql;
	private static String namespace = "com.movielog.mappers.comment";

	  @Override
	  public List<CommentVO> list(Integer bno, Criteria cri)
	      throws Exception {

	    Map<String, Object> paramMap = new HashMap<>();

	    paramMap.put("bno", bno);
	    paramMap.put("cri", cri);

	    return sql.selectList(namespace + ".list", paramMap);
	  }


	  @Override
	  public void create(CommentVO vo) throws Exception {

		  sql.insert(namespace + ".create", vo);
	  }

	  @Override
	  public void update(CommentVO vo) throws Exception {

		  sql.update(namespace + ".update", vo);
	  }

	  @Override
	  public void delete(Integer cno) throws Exception {

		  sql.update(namespace + ".delete", cno);
	  }

	  @Override
	  public int count(Integer bno) throws Exception {

	    return sql.selectOne(namespace + ".count", bno);
	  }

}
