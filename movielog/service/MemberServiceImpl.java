package com.movielog.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.movielog.dao.MemberDAO;
import com.movielog.domain.LoginVO;
import com.movielog.domain.MailUtils;
import com.movielog.domain.MemberVO;
import com.movielog.domain.TempKey;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;

	@Override
	public int idCnt(MemberVO memberVO) throws Exception {
		return dao.idCnt(memberVO);
	}

	@Override
	public MemberVO login(LoginVO loginVO) throws Exception {
		return dao.login(loginVO);
	}

	@Override
	public void keepLogin(String userid, String sessionid, Date sessionlimit) throws Exception {
		dao.keepLogin(userid, sessionid, sessionlimit);

	}

	public MemberVO checkLoginBefore(String value) throws Exception {
		return dao.checkUserWithSessionKey(value);
	}

	@Inject
	private JavaMailSender mailSender;

	@Transactional
	@Override
	public void register(MemberVO memberVO) throws Exception {
		dao.register(memberVO);

		String key = new TempKey().getKey(50, false);
		dao.createAuthKey(memberVO.getUseremail(), key);
		MailUtils sendMail = new MailUtils(mailSender);
		sendMail.setSubject("[MOVIELOG 이메일 인증메일 입니다.]"); // 메일제목
		sendMail.setText("<h1>메일인증</h1>" + "<br/>" + memberVO.getUserid() + "님 " + "<br/>MOVIELOG에 회원가입해주셔서 감사합니다."
				+ "<br/>아래 [이메일 인증 확인]을 눌러주세요." + "<a href='http://localhost:8080/member/registerEmail?useremail="
				+ memberVO.getUseremail() + "&key=" + key + "' target='_blenk'>이메일 인증 확인</a>");
		sendMail.setFrom("movieelog@gmail.com", "무비로그");
		sendMail.setTo(memberVO.getUseremail());
		sendMail.send();

	}

	@Override
	public void memberAuth(String useremail) throws Exception {
		dao.memberAuth(useremail);
	}

	@Override
	public List<MemberVO> findId(String useremail) throws Exception {
		return dao.findId(useremail);
	}

	@Override
	public int findIdCheck(String useremail) throws Exception {
		return dao.findIdCheck(useremail);
	}

	@Override
	public int findPwCheck(MemberVO memberVO) throws Exception {
		return dao.findPwCheck(memberVO);
	}

	@Override
	public void findPw(String useremail, String userid) throws Exception {
		String userKey = new TempKey().getKey(6, false);
		String userpw = BCrypt.hashpw(userKey, BCrypt.gensalt());
		dao.findPw(useremail, userid, userpw);
		MailUtils sendMail = new MailUtils(mailSender);
		sendMail.setSubject("[MOVIELOG 임시 비밀번호 입니다.]"); // 메일제목
		sendMail.setText("<h1>임시비밀번호 발급</h1>" + "<br/>" + userid + "님 " + "<br/>비밀번호 찾기를 통한 임시 비밀번호입니다."
				+ "<br/>임시비밀번호 :   <h2>" + userKey + "</h2>" + "<br/>로그인 후 비밀번호 변경을 해주세요."
				+ "<a href='http://localhost:8080/member/loginView" + ">로그인 페이지</a>");
		sendMail.setFrom("movieelog@gmail.com", "무비로그");
		sendMail.setTo(useremail);
		sendMail.send();
	}
	
	@Override
	public void infoUpdate(MemberVO memberVO)throws Exception{
		dao.infoUpdate(memberVO);
	}

	@Override
	public int nameCheck(MemberVO memberVO)throws Exception{
		return dao.nameCheck(memberVO);
	}

	
	@Override
	public String pwCheck(String userid)throws Exception{
		return dao.pwCheck(userid);
	}
	
	@Override
	public void pwUpdate(String userid, String hashedPw)throws Exception{
		dao.pwUpdate(userid, hashedPw);
	}
	
	@Override
	public void delete(String userid)throws Exception{
		dao.delete(userid);
	}

	@Override
	public void updateImg(String userimg, String userid) throws Exception {
		dao.updateImg(userimg, userid);
	}

	



}
