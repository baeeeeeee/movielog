package com.movielog.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.movielog.domain.BoardVO;
import com.movielog.domain.CateVO;
import com.movielog.domain.MemberVO;
import com.movielog.domain.PageMaker;
import com.movielog.domain.SearchCriteria;
import com.movielog.service.BoardService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	private BoardService service;
	
	// 등록 페이지 이동
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeGET(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria, Model model) throws Exception {
		
		List<CateVO> category = null;
		category = service.category();
		model.addAttribute("category", JSONArray.fromObject(category));
		
	    return "/board/write";
	}
	
	// 등록 처리
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePOST(BoardVO boardVO,RedirectAttributes rttr,@ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		
		service.create(boardVO);
		
		rttr.addAttribute("bgno", searchCriteria.getBgno());
	
		return "redirect:/board/read?bno="+ boardVO.getBno();
	}

	// 목록 페이지 이동
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria, Model model) throws Exception {
		List<CateVO> category = service.category();
		model.addAttribute("category", JSONArray.fromObject(category));
		
		PageMaker pageMaker = new PageMaker();
	    pageMaker.setCriteria(searchCriteria);
	   
	    pageMaker.setTotalCount(service.countArticles(searchCriteria));

	    model.addAttribute("list", service.list(searchCriteria));
	    model.addAttribute("pageMaker", pageMaker);
	    model.addAttribute("notice", service.notice());

		return "/board/list";
	}
	 
	// 조회 페이지 이동
	 @RequestMapping(value = "/read", method = RequestMethod.GET)
	 public String read(@RequestParam("bno") int bno, Model model, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria) throws Exception {
		 
	     model.addAttribute("board", service.read(bno));


	     return "/board/read";
	 }
	 
	// 수정 페이지 이동
	 @RequestMapping(value = "/modify", method = RequestMethod.GET)
	 public String modifyGET(@RequestParam("bno") int bno, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria, Model model) throws Exception {
	     
		 model.addAttribute("board", service.read(bno));

	     return "/board/modify";
	 }
	 
	// 수정 처리
	 @RequestMapping(value = "/modify", method = RequestMethod.POST)
	 public String modifyPOST(BoardVO boardVO, RedirectAttributes rttr, SearchCriteria searchCriteria) throws Exception {

		service.update(boardVO);
		 
		rttr.addAttribute("bno", boardVO.getBno());
		rttr.addAttribute("bgno", searchCriteria.getBgno());
			 
		rttr.addAttribute("page", searchCriteria.getPage());
		rttr.addAttribute("perPageNum", searchCriteria.getPerPageNum());
		rttr.addAttribute("searchType", searchCriteria.getSearchType());
		rttr.addAttribute("keyword", searchCriteria.getKeyword());

		return "redirect:/board/read";
	 }
	 
	// 삭제 처리
	 @RequestMapping(value = "/delete", method = RequestMethod.GET)
	 public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr, SearchCriteria searchCriteria) throws Exception {

		 service.delete(bno);
		 
		 rttr.addAttribute("bgno", searchCriteria.getBgno());
		 rttr.addAttribute("page", searchCriteria.getPage());
		 rttr.addAttribute("perPageNum", searchCriteria.getPerPageNum());
		 rttr.addAttribute("searchType", searchCriteria.getSearchType());
		 rttr.addAttribute("keyword", searchCriteria.getKeyword());
		 rttr.addFlashAttribute("msg", "delSuccess");

	     return "redirect:/board/list";
	 }
	 
	// 썸머노트
	 @RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
		@ResponseBody
		public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
			JsonObject jsonObject = new JsonObject();
			
	     
			String fileRoot = "C:\\summernote_image\\"; // 외부경로로 저장을 희망할때.
			String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
			String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
			String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
			
			File targetFile = new File(fileRoot + savedFileName);	
			try {
				InputStream fileStream = multipartFile.getInputStream();
				FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
				jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
				jsonObject.addProperty("responseCode", "success");
					
			} catch (IOException e) {
				FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
				jsonObject.addProperty("responseCode", "error");
				e.printStackTrace();
			}
			String a = jsonObject.toString();
			return a;
		}
	 

	 
	 

}
