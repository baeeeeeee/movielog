package com.movielog.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {

	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	private Date userjoin;
	private String userimg;
	private String userid_yn;
	private String username_yn;
	private int member_auth;
	
}
