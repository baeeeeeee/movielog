package com.movielog.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.movielog.domain.LoginVO;
import com.movielog.domain.MemberVO;

public interface MemberService {

	public void register(MemberVO memberVO) throws Exception;

	public int idCnt(MemberVO memberVO) throws Exception;

	public MemberVO login(LoginVO loginVO) throws Exception;

	void keepLogin(String userid, String sessionid, Date sessionlimit) throws Exception;

	public MemberVO checkLoginBefore(String value) throws Exception;

	public void memberAuth(String useremail) throws Exception;

	public List<MemberVO> findId(String useremail) throws Exception;

	public int findIdCheck(String useremail) throws Exception;

	public int findPwCheck(MemberVO memberVO) throws Exception;

	public void findPw(String useremail, String userid) throws Exception;

	public void infoUpdate(MemberVO memberVO) throws Exception;

	public int nameCheck(MemberVO memberVO) throws Exception;

	public String pwCheck(String userid) throws Exception;

	public void pwUpdate(String userid, String hashedPw) throws Exception;
	
	public void delete(String userid)throws Exception;
	
	public void updateImg(String userimg, String userid) throws Exception;


}
