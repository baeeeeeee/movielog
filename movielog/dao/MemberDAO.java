package com.movielog.dao;

import java.util.Date;
import java.util.List;

import com.movielog.domain.LoginVO;
import com.movielog.domain.MemberVO;

public interface MemberDAO {
	public void register(MemberVO memberVO) throws Exception;
	
	public int idCnt(MemberVO memberVO)throws Exception;
	
	public MemberVO login(LoginVO loginVO)throws Exception;
	
	// 로그인 유지 처리
	void keepLogin(String userid, String sessionid, Date sessionlimit) throws Exception;

	// 세션키 검증
	MemberVO checkUserWithSessionKey(String value) throws Exception;
	
	public void createAuthKey(String useremail,String authKey) throws Exception;
	
	public void memberAuth(String useremail) throws Exception;
	
	public List<MemberVO> findId(String useremail)throws Exception;
	
	public int findIdCheck(String useremail)throws Exception;
	
	public int findPwCheck(MemberVO memberVO)throws Exception; 
	
	public int findPw(String userpw,String useremail,String userid)throws Exception;
	
	public void infoUpdate(MemberVO memberVO)throws Exception;
	
	public int nameCheck(MemberVO memberVO)throws Exception;
	
	public String pwCheck(String userid)throws Exception;
	
	public void pwUpdate(String hashedPw, String userid)throws Exception;
	
	public void delete(String userid)throws Exception;
	
	public void updateImg(String userimg, String userid) throws Exception;
	
}
