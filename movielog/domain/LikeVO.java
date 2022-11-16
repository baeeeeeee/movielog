package com.movielog.domain;

import java.util.Date;

import lombok.Data;

@Data
public class LikeVO {

	private int likeno;
	private int bno;
	private String userid;
	private int rno;
	private int likecheck;
	private Date likedate;
	
	

}
