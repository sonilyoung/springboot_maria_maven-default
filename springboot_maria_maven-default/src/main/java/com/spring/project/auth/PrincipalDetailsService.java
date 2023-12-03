
package com.spring.project.auth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private LoginMapper testMapper;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
	Date time = new Date();
	String localTime = format.format(time);


	// .usernameParameter("userId")

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		//Optional<UserVo> memberEntityWrapper = testMapper.findByAccount(userId);
		//UserVo userEntity = memberEntityWrapper.orElse(null);
		UserVo user = testMapper.findByAccount(userId);
		if (user == null) {
			return null;
		} else {
			return new PrincipalDetails(user);
		}
	}

}
