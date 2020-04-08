package com.clinicnetwork.catalogmanager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.clinicnetwork.catalogmanager.model.User_;
import com.clinicnetwork.catalogmanager.service.api.IUserService;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
public class UserControllerTest 
{
	private static final String URL = "/api/users/";
	
	private MockMvc mockMvc;		
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();	
	
	@InjectMocks
	UserController userController;
	
	@Mock
	IUserService userService;	
	
	User_ user = new User_("camila19",  bCryptPasswordEncoder.encode("1234") , "Camila Erazo" , "camila19@gmail.com"  ,  bCryptPasswordEncoder.encode("1234"));
	User_ user2 = new User_("camila20",  bCryptPasswordEncoder.encode("1234") , "Camila Chávez" , "camila19@gmail.com"  ,  bCryptPasswordEncoder.encode("1234"));
	User_ user3 = new User_("camila21",  bCryptPasswordEncoder.encode("1234") , "María Erazo" , "camila19@gmail.com"  ,  bCryptPasswordEncoder.encode("1234"));
	User_ user4 = new User_("camila22",  bCryptPasswordEncoder.encode("1234") , "María Chávez" , "camila19@gmail.com"  ,  bCryptPasswordEncoder.encode("1234"));
	
	@BeforeEach
	public void setUp()
	{			
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();		
		MockitoAnnotations.initMocks(this);				
	}
	
	@Test
	public void testGetAll() throws Exception
	{
		List<User_> returnList = new ArrayList<User_>();
		returnList.add(user);
		returnList.add(user2);
		returnList.add(user3);
		returnList.add(user4);
		when(userService.getAll()).thenReturn(returnList);
		mockMvc.perform(get(URL+"all" )).andExpect(status().isOk());
		
	}
	
	@Test
	public void testGet() throws Exception
	{	
		when(userService.get(anyString())).thenReturn(user);
		mockMvc.perform(get(URL+"/search/{login}" , "camila19" )).andExpect(status().isOk());		
	}	
		
}
