package com.clinicnetwork.catalogmanager.service.impl;

import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.clinicnetwork.catalogmanager.dao.api.IUserDao;
import com.clinicnetwork.catalogmanager.model.User_;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService 
{
	@Autowired
	IUserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException 
	{		
		User_ appUser = userDao.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Incorrect login"));
				
		Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();
				
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN");
		
		grantList.add(grantedAuthority);
		
		UserDetails user = (UserDetails) new User(login , appUser.getPassword(), grantList);
		
		return user;		
	}

}
