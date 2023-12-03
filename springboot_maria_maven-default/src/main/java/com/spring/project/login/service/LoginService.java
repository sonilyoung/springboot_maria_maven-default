
package com.spring.project.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.login.mapper.LoginMapper;
import com.spring.project.login.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

/**
 * Service
 */

@Slf4j

@Service
public class LoginService{

	@Autowired
	private LoginMapper loginMapper;

	/**
	 * 회원정보 조회(중개회원 및 중개업소)
	 * 
	 * @param userId
	 * @return
	 */

	public UserVo selectUserInfo(String userId) {
		return loginMapper.selectUserInfo(userId);
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	public List<UserVo> getAllDataList(UserVo testVo) {
		return loginMapper.getAllDataList(testVo);
	}

	@Transactional
	public void insertUser(UserVo UserVo) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		UserVo.setPassword(passwordEncoder.encode(UserVo.getPassword()));
		UserVo.setUserAuth("ROLE_USER");
		loginMapper.insertUser(UserVo);

	}

}
