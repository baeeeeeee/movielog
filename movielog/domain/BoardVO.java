package com.movielog.domain;

import java.util.Date;

public class BoardVO {

	/* 게시판 번호 */
	private Integer bno;

	/* 게시판 제목 */
	private String title;

	/* 댓글수 */
	private int replycnt;

	/* 게시판 내용 */
	private String content;

	/* 게시판 작가 */
	private String writer;

	/* 등록 날짜 */
	private Date regdate;

	/* 등록 날짜 */
	private String date;

	/* 조회수 */
	private int viewcnt;

	/* 다중게시판 */
	private int bgno;
	
	private int likehit;

	/* 카테고리 코드 */
	private String cateCode;

	/* 카테고리 이름 */
	private String cateName;

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReplycnt() {
		return replycnt;
	}

	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = Time.calculateTime(date);
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public int getBgno() {
		return bgno;
	}

	public void setBgno(int bgno) {
		this.bgno = bgno;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", replycnt=" + replycnt + ", content=" + content
				+ ", writer=" + writer + ", regdate=" + regdate + ", date=" + date + ", viewcnt=" + viewcnt + ", bgno="
				+ bgno + ", cateCode=" + cateCode + ", cateName=" + cateName + "]";
	}

	public int getLikehit() {
		return likehit;
	}

	public void setLikehit(int likehit) {
		this.likehit = likehit;
	}

}
