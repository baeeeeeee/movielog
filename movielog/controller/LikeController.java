package com.movielog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.movielog.service.LikeService;

@Controller
public class LikeController {
	   @Autowired
	    LikeService service;
	   
		@ResponseBody
		@RequestMapping(value = "/board/updateLike" , method = RequestMethod.POST)
		public int updateLike(int bno, String userid)throws Exception{
			
				int likeCheck = service.likeCheck(bno, userid);
				if(likeCheck == 0) {
					//좋아요 처음누름
					service.insertLike(bno, userid); //like테이블 삽입
					service.updateLike(bno);	//게시판테이블 +1
					service.updateLikeCheck(bno, userid);//like테이블 구분자 1
				}else if(likeCheck == 1) {
					service.updateLikeCheckCancel(bno, userid); //like테이블 구분자0
                    service.updateLikeCancel(bno); //게시판테이블 - 1
					service.deleteLike(bno, userid); //like테이블 삭제
				}
				return likeCheck;
		}
}
