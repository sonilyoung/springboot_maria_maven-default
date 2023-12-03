package com.spring.project.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.auth.PrincipalDetailsService;
import com.spring.project.login.vo.UserVo;

@Controller
public class LoginController {
	
	
	@Autowired
	private PrincipalDetailsService testService;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView goLogin(HttpServletRequest request
			,UserVo testVo
			,@RequestParam(value = "error", required = false) String error
			,@RequestParam(value = "exception", required = false) String exception
			,Model model
			) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginPage");
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);		
		return mav;
	}	
	
	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, UserVo testVo) {
		ModelAndView mav = new ModelAndView();
		
		List<UserVo> resultList= testService.getAllDataList(testVo);
		
		mav.addObject("resultList",resultList);
		mav.setViewName("index");
		
		return mav;
	}
	
	/**
	 * 로그인 처리 
	 * @return
	 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(TestVo testVo) {
		
 		JwtToken token = testService.procLogin(testVo.getId(), testVo.getPassword());
 		ModelAndView mav = new ModelAndView();
		
		mav.addObject("token",token.getAccessToken());
		mav.addObject("refreshToken",token.getRefreshToken());
		mav.setViewName("index");
		return mav;
	}*/
	
	@RequestMapping(value = "/loginPage", method=RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request, UserVo testVo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginPage");
		return mav;
	}		
	
	@RequestMapping(value = "/signUp", method=RequestMethod.GET)
	public ModelAndView signUp(HttpServletRequest request, UserVo testVo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("join");
		return mav;
	}	
	
	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public ModelAndView join(HttpServletRequest request, UserVo testVo) {
		ModelAndView mav = new ModelAndView();
		testService.joinUser(testVo);
		mav.setViewName("index");
		return mav;
	}	
}
