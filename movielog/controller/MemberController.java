package com.movielog.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;


import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movielog.domain.LoginVO;
import com.movielog.domain.MemberVO;
import com.movielog.domain.fileUtil;
import com.movielog.service.MemberService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Inject
	private MemberService service;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() throws Exception {
		return "/member/join";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() throws Exception {
		return "/member/test";
	}

	@RequestMapping(value = "/errorLogin", method = RequestMethod.GET)
	public String errorLogin() throws Exception {
		return "/member/errorLogin";
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info() throws Exception {
		return "/member/info";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(MemberVO memberVO, RedirectAttributes rttr,HttpServletRequest req) throws Exception {
		String hashedPw = BCrypt.hashpw(memberVO.getUserpw(), BCrypt.gensalt());
		memberVO.setUserpw(hashedPw);
		service.register(memberVO);

		rttr.addFlashAttribute("msg", "가입이 완료되었습니다.");

		rttr.addAttribute("useremail", memberVO.getUseremail());
		rttr.addAttribute("userid", memberVO.getUserid());

		return "redirect:/member/registerAuth";
	}

	@RequestMapping(value = "/idCnt", method = RequestMethod.POST)
	@ResponseBody
	public String idCnt(@RequestBody String filterJSON, HttpServletResponse response, ModelMap model) throws Exception {
		JSONObject resMap = new JSONObject();

		try {
			ObjectMapper mapper = new ObjectMapper();
			MemberVO searchVO = (MemberVO) mapper.readValue(filterJSON, new TypeReference<MemberVO>() {
			});
			int idCnt = service.idCnt(searchVO);

			resMap.put("res", "ok");
			resMap.put("idCnt", idCnt);

		} catch (Exception e) {
			System.out.println(e.toString());
			resMap.put("res", "error");
		}

		response.setContentType("text/html: charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resMap);

		return null;
	}

	// 닉네임 유효성 체크
	@RequestMapping(value = "/nameCheck", method = RequestMethod.POST)
	@ResponseBody
	public String nameCnt(@RequestBody String filterJSON, HttpServletResponse response, Model model) throws Exception {
		JSONObject resMap = new JSONObject();
		try {
			ObjectMapper mapper = new ObjectMapper();
			MemberVO searchVO = (MemberVO) mapper.readValue(filterJSON, new TypeReference<MemberVO>() {
			});

			int nameCnt = service.nameCheck(searchVO);
			resMap.put("res", "ok");
			resMap.put("nameCnt", nameCnt);
		} catch (Exception e) {
			System.out.println(e.toString());
			resMap.put("res", "error");
		}
		response.setContentType("text/html: charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(resMap);

		return null;
	}

	@RequestMapping(value = "/loginView", method = RequestMethod.GET)
	public String loginView(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, Model model)
			throws Exception {

		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (null != inputFlashMap) {
			model.addAttribute("msg", (String) inputFlashMap.get("msg"));
		}
		return "/member/loginView";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(LoginVO loginVO, HttpSession httpSession, Model model) throws Exception {
		MemberVO memberVO = service.login(loginVO);
		if (memberVO == null || !BCrypt.checkpw(loginVO.getUserpw(), memberVO.getUserpw())) {
			return "/member/loginCheck";
		}

		if (memberVO.getMember_auth() == 0) {
			model.addAttribute("Auth", memberVO.getMember_auth());
			return "/member/registerReady";
		}

		model.addAttribute("memberVO", memberVO);

		if (loginVO.isUseCookie()) {
			int amount = 60 * 60 * 24 * 7;
			Date sessionlimit = new Date(System.currentTimeMillis() + (1000 * amount));
			service.keepLogin(memberVO.getUserid(), httpSession.getId(), sessionlimit);
		}

		return "/board/list";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpSession session, HttpServletResponse response, ModelMap model)
			throws Exception {

		Object URL = session.getAttribute("URL");
		Object object = session.getAttribute("login");
		if (object != null) {
			MemberVO memberVO = (MemberVO) object;
			session.removeAttribute("login");
			session.invalidate();
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(memberVO.getUserid(), "none", new Date());

			}
		}
		String requestURL = "/";
		if (StringUtils.isNotBlank((String) URL))
			requestURL = (String) URL;

		return "redirect:" + requestURL;
	}

	@RequestMapping(value = "/registerEmail", method = RequestMethod.GET)
	public String emailConfirm(String useremail, Model model) throws Exception {
		service.memberAuth(useremail);
		model.addAttribute("useremail", useremail);

		return "/member/registerEmail";
	}

	@RequestMapping(value = "/registerAuth", method = RequestMethod.GET)
	public String loginView(HttpServletRequest request, Model model, @RequestParam("useremail") String useremail,
			@RequestParam("userid") String userid) throws Exception {

		model.addAttribute("useremail", useremail);
		model.addAttribute("userid", userid);

		return "/member/registerAuth";
	}

	@RequestMapping(value = "/findIdView", method = RequestMethod.GET)
	public String findIdView() throws Exception {
		return "/member/findIdView";
	}

	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	public String findId(MemberVO memberVO, Model model) throws Exception {

		if (service.findIdCheck(memberVO.getUseremail()) == 0) {
			model.addAttribute("msg", "이메일을 확인해주세요");
			return "/member/findIdView";
		} else {
			model.addAttribute("member", service.findId(memberVO.getUseremail()));
			return "/member/findId";
		}
	}

	@RequestMapping(value = "/findPwView", method = RequestMethod.GET)
	public String findPwView() throws Exception {
		return "/member/findPwView";
	}

	@RequestMapping(value = "/findPw", method = RequestMethod.POST)
	public String findPw(MemberVO memberVO, Model model) throws Exception {
		if (service.findPwCheck(memberVO) == 0) {
			model.addAttribute("msg", "아이디와 이메일을 확인해주세요");

			return "/member/findPwView";
		} else {

			service.findPw(memberVO.getUseremail(), memberVO.getUserid());
			model.addAttribute("member", memberVO.getUseremail());

			return "/member/findPw";
		}
	}

	// 회원정보수정뷰
	@RequestMapping(value = "/infoView", method = RequestMethod.GET)
	public String infoView() throws Exception {
		return "/member/infoView";
	}

	// 회원정보수정로직
	@RequestMapping(value = "/infoUpdate", method = RequestMethod.POST)
	public String infoUpdate(HttpServletRequest request, HttpSession session, MemberVO memberVO, Model model,
			RedirectAttributes rttr) throws Exception {
		service.infoUpdate(memberVO);
		session.invalidate();
		rttr.addFlashAttribute("msg", "정보 수정이 완료되었습니다. 다시 로그인해주세요.");
		return "redirect:/member/loginView";
	}

	@RequestMapping(value = "/pwUpdateView", method = RequestMethod.GET)
	public String pwUpdateView() throws Exception {
		return "/member/pwUpdateView";
	}

	@RequestMapping(value = "/pwCheck", method = RequestMethod.POST)
	@ResponseBody
	public int pwCheck(MemberVO memberVO) throws Exception {
		String userpw = service.pwCheck(memberVO.getUserid());
		if (memberVO == null || !BCrypt.checkpw(memberVO.getUserpw(), userpw)) {
			return 0;
		}
		return 1;
	}

	@RequestMapping(value = "/pwUpdate", method = RequestMethod.POST)

	public String pwUpdate(String userid, String userpw1, RedirectAttributes rttr, HttpSession session)
			throws Exception {
		String hashedPw = BCrypt.hashpw(userpw1, BCrypt.gensalt());
		service.pwUpdate(hashedPw, userid);
		session.invalidate();
		rttr.addFlashAttribute("msg", "정보 수정이 완료되었습니다. 다시 로그인해주세요.");

		return "redirect:/member/loginView";
	}

	@RequestMapping(value = "/deleteView", method = RequestMethod.GET)
	public String deleteView() throws Exception {
		return "/member/deleteView";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String userid, RedirectAttributes rttr, HttpSession session) throws Exception {
		service.delete(userid);
		session.invalidate();
		rttr.addFlashAttribute("msg", "이용해주셔서 감사합니다.");
		return "redirect:/member/loginView";
	}
	
	@RequestMapping(value = "/infoimgView", method = RequestMethod.GET)
	public String infoimgView() throws Exception {
		return "/member/infoimgView";
	}
	
	@RequestMapping(value="/updateImg", method=RequestMethod.POST)
	public String updateImg(MultipartHttpServletRequest mpRequest, HttpSession session , String userid)throws Exception {
		
		String userimg = fileUtil.updateImg(mpRequest); 

		MemberVO memberVO = (MemberVO) session.getAttribute("login");
		
		service.updateImg(userimg, userid);
		
		memberVO.setUserimg(userimg);
		session.setAttribute("login", memberVO);
		
				
		return "/member/infoimgView";
	}
	

}
