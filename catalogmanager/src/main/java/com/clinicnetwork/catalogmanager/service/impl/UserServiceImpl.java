package com.clinicnetwork.catalogmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.clinicnetwork.catalogmanager.commons.GenericServiceImpl;
import com.clinicnetwork.catalogmanager.dao.api.IUserDao;
import com.clinicnetwork.catalogmanager.model.User_;
import com.clinicnetwork.catalogmanager.service.api.IUserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User_, String> implements IUserService
{
	@Autowired
	private IUserDao userDao;

	@Override
	public CrudRepository<User_, String> getDao() 
	{		
		return userDao;
	}	
}
