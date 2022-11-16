package com.movielog.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommentVO {

	private Integer cno;
	private Integer bno;
	private String content;
	private String writer;
	private Date regdate;
}
