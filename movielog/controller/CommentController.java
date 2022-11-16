package com.movielog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movielog.domain.CommentVO;
import com.movielog.domain.Criteria;
import com.movielog.domain.PageMaker;
import com.movielog.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService service;

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody CommentVO vo) {

      ResponseEntity<String> entity = null;
      try {
        service.create(vo);
        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
      return entity;
    }
    

    @RequestMapping(value = "/edit/{cno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    public ResponseEntity<String> update(@PathVariable("cno") Integer cno, @RequestBody CommentVO vo) {

      ResponseEntity<String> entity = null;
      try {
        vo.setCno(cno);
        service.update(vo);

        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
      return entity;
    }

    @RequestMapping(value = "/delete/{cno}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable("cno") Integer cno) {

      ResponseEntity<String> entity = null;
      try {
        service.delete(cno);
        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
      return entity;
    }
    
 
    @RequestMapping(value = "/pages/{bno}/{page}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> listPage(
        @PathVariable("bno") int bno,
        @PathVariable("page") int page) {

      ResponseEntity<Map<String, Object>> entity = null;
      
      try {
        Criteria cri = new Criteria();
        cri.setPage(page);

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(cri);

        Map<String, Object> map = new HashMap<String, Object>();
        List<CommentVO> list = service.list(bno, cri);

        map.put("list", list);

        int replyCount = service.count(bno);
        pageMaker.setTotalCount(replyCount);

        map.put("pageMaker", pageMaker);

        entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
      }
      return entity;
    }


}