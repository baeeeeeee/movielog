package com.movielog.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.movielog.domain.LoginVO;
import com.movielog.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sql;
	
	private static String namespace = "com.movielog.mappers.member";

	@Override
	public void register(MemberVO memberVO) throws Exception {
		sql.insert(namespace + ".register", memberVO);

	}

	@Override
	public int idCnt(MemberVO memberVO) throws Exception {
		return sql.selectOne(namespace +".idCnt", memberVO);
	}
	
	@Override
	public MemberVO login(LoginVO loginVO)throws Exception{
		return sql.selectOne(namespace + ".login", loginVO);
	}
	
	@Override
	public void keepLogin(String userid,String sessionid, Date sessionlimit)throws Exception{
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userid", userid);
		paramMap.put("sessionid", sessionid);
		paramMap.put("sessionlimit", sessionlimit);
		
		sql.update(namespace+ ".keepLogin", paramMap);
	}
	
	@Override
	public MemberVO checkUserWithSessionKey(String value) throws Exception{
		return sql.selectOne(namespace+".check", value);
	}

	@Override
	public void createAuthKey(String useremail, String authKey) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("useremail", useremail);
		map.put("authKey", authKey);
		
		sql.selectOne(namespace+ ".createAuthKey", map);
		
	}

	@Override
	public void memberAuth(String useremail) throws Exception {
		sql.update(namespace+".memberAuth", useremail);
		
	}

	@Override
	public List<MemberVO> findId(String useremail) throws Exception {
		return sql.selectList(namespace+".findId", useremail);
	}

	@Override
	public int findIdCheck(String useremail) throws Exception {
		return sql.selectOne(namespace+".findIdCheck", useremail);
	}

	@Override
	public int findPwCheck(MemberVO memberVO) throws Exception {
		return sql.selectOne(namespace+".findPwCheck", memberVO);	
	}

	@Override
	public int findPw(String userpw, String useremail, String userid) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("useremail", useremail);
		map.put("userid", userid);
		map.put("userpw", userpw);
		return sql.update(namespace+".findPw", map);
	}

	@Override
	public void infoUpdate(MemberVO memberVO)throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("useremail", memberVO.getUseremail());
		map.put("username", memberVO.getUsername());
		map.put("userid", memberVO.getUserid());
		
		sql.update(namespace+".infoUpdate", map);
	}
	
	@Override
	public int nameCheck(MemberVO memberVO)throws Exception{
		
		return sql.selectOne(namespace+".nameCheck", memberVO);
	}
	
	@Override
	public String pwCheck(String userid)throws Exception{
		return sql.selectOne(namespace+".pwCheck", userid);
	}
	
	@Override
	public void pwUpdate(String hashedPw, String userid) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userpw", hashedPw);
		map.put("userid", userid);
		sql.update(namespace+".pwUpdate", map);
		
	}
	

	@Override
	public void delete(String userid)throws Exception{
		sql.delete(namespace+".delete", userid);
	}

	@Override
	public void updateImg(String userimg, String userid) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userimg", userimg);
		map.put("userid", userid);
		sql.update(namespace+".updateImg", map);
		
	}

	


}
