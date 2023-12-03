package com.spring.project.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.project.login.vo.UserVo;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails {//UserDetails는 시큐리티가 관리하는 객체이다.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private UserVo user;

    // 일반 로그인
    public PrincipalDetails(UserVo user) {
        this.user = user;
    }	
	
	//private LocalDateTime userPwChgDt; // 비밀번호변경일
	//private int loginFailCnt; // 로그인실패횟수
	//private List<String> roles = new ArrayList<>();
	
	
	
	//success code
    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.userAuth));
    }*/
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getUserAuth();
            }
        });
        return collection;
	}
	
    
    /*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userAuth.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    } */   
    
	/**
	 * 패스워드 변경 필요여부(90일 기준)
	 * @return
	 */
	public boolean isPwChgRequired() {
		//if (!SecurityUtil.isAuthenticated()) {
			//return this.userPwChgDt.isBefore(DateUtil.getMinusDayNowDt(90));
			//return true;
		//} else {
			return false;
		//}
	}
		
	public UserVo getUser() {
		return user;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserNm();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
