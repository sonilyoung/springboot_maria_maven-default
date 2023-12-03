package com.spring.project.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.project.login.service.LoginService;
import com.spring.project.login.vo.UserVo;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = {"/", "/main"}, method=RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, UserVo userVo) {
		ModelAndView mav = new ModelAndView();
		//List<UserVo> resultList= testService.getAllDataList(userVo);
		//mav.addObject("resultList",resultList);
		mav.setViewName("main");
		return mav;
	}
	
	/**
	 * 로그인 처리 
	 * @return
	 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(userVo userVo) {
		
 		JwtToken token = testService.procLogin(userVo.getId(), userVo.getPassword());
 		ModelAndView mav = new ModelAndView();
		
		mav.addObject("token",token.getAccessToken());
		mav.addObject("refreshToken",token.getRefreshToken());
		mav.setViewName("index");
		return mav;
	}*/
	
	@RequestMapping(value = "/loginPage", method=RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request, UserVo userVo
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
	
	@RequestMapping(value = "/joinPage", method=RequestMethod.GET)
	public ModelAndView joinPage(HttpServletRequest request, UserVo userVo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("joinPage");
		return mav;
	}	
	
	@RequestMapping(value = "/joinProc", method=RequestMethod.POST)
	public String joinProc(HttpServletRequest request, UserVo userVo) {
		loginService.insertUser(userVo);
		return "redirect:/main";
	}	
}
