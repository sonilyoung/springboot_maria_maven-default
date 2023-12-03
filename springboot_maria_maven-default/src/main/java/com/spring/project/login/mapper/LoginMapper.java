package com.spring.project.login.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.spring.project.login.vo.UserVo;

/**
* 테스트 Mapper
*/
@Mapper
public interface LoginMapper {

	/**
	 * 테스트
	 * @param 
	 * @return
	 */
	List<UserVo> getAllDataList(UserVo testVo);
	
	
	/**
	 * 테스트
	 * @param 
	 * @return
	 */
	UserVo getUserInfo(String userId);	

	
	/**
	 * 테스트
	 * @param 
	 * @return
	 */
	UserVo findByAccount(String userId);
	
	/**
	 * 테스트
	 * @param 
	 * @return
	 */	
	void saveUser(UserVo testVo);
	

}
