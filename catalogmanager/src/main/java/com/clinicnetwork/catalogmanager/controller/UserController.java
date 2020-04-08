package com.clinicnetwork.catalogmanager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clinicnetwork.catalogmanager.model.User_;
import com.clinicnetwork.catalogmanager.service.api.IUserService;

@RestController
@RequestMapping(value = "/api/users/")
public class UserController 
{
	@Autowired
	private IUserService userService;
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<User_>> getAll()
	{
		List<User_> users =  userService.getAll();		
		
		return new ResponseEntity<>(users, HttpStatus.OK);		
	}
	
	@GetMapping(value = "/search/{login}")
	public ResponseEntity<User_> get(@PathVariable String login)
	{
		System.out.println("login: " + login);
		User_ user = userService.get(login);
		//System.out.println("user: " + user.getLogin());
		if(user != null)
		{
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody User_ user)
	{
		User_ existUser = userService.get(user.getLogin());
		
		if(existUser != null)
		{
			return new ResponseEntity<>("User already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else if (user.getPassword() == null || user.getConfirmPassword() == null || user.getName() == null || user.getLogin() == null || user.getEmail() == null) 
		{
			return new ResponseEntity<>("User couldn't be saved: there are null values!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else if(!user.getPassword().equals(user.getConfirmPassword()) )
		{
			return new ResponseEntity<>("Passwords don't match", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			
			userService.save(user);
			
			return new ResponseEntity<>("User saved successfully!", HttpStatus.OK);
		}

	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<String> update(@RequestBody User_ user)
	{
		User_ optUser = userService.get(user.getLogin());
		if(optUser != null)
		{
			User_ updUser = optUser;			
			
			if(user.getEmail() != null)
			{
				updUser.setEmail(user.getEmail());
			}
			
			if(user.getName() != null)
			{
				updUser.setName(user.getName());
			}
			
			if(user.getPassword() != null)
			{
				if(user.getPassword().equals(user.getConfirmPassword()))
				{					
					updUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				}
				else
				{
					return new ResponseEntity<>("Passwords don't match!", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
			userService.save(updUser);
			
			return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);			
		}
		else
		{
			return new ResponseEntity<>("User not found!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping(value = "/delete/{login}") 
	public ResponseEntity<String> delete (@PathVariable String login )
	{
		User_ user = userService.get(login);
		
		if (user != null) 
		{
			userService.delete(login);
			return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("User not found!", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}
}
