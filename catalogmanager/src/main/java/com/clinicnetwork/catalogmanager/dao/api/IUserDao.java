package com.clinicnetwork.catalogmanager.dao.api;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.clinicnetwork.catalogmanager.model.User_;

public interface IUserDao extends CrudRepository<User_, String> 
{
	public Optional<User_>  findByLogin(String login);
}
