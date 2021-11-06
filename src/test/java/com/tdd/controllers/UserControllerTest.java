package com.tdd.controllers;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.models.Users;
import com.tdd.service.UserService;

//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = UserController.class)


//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages  = "com.tdd")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {UserControllerTest.class})
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
	Users user;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	} 
	
	/*@Test
	public void verifyUserTest() throws Exception{
		
		when(userService.getString()).thenReturn("from test");
		
		//System.out.println(mockMvc);
		mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("hello"));
		
	}
	
	@Test
    public void testPostTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/testPost")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content("{ \"accountType\": \"SAVINGS\", \"balance\": 5000.0 }") 
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
	
	@Test
    public void testJsonPostTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/testJsonReturn")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content("{ \"username\": \"abc\",\"emailId\":\"abc@abc.com\", \"balance\": 5000.0 }") 
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("abc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailId").value("abc@abc.com"))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.balance").value("5000.0"));
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("abc")) // If return json array then use $.[0] ie 1st element of array
        		//.andExpect(MockMvcResultMatchers.jsonPath("$[0].balance").value("5000.0"));
        
    }*/
	
	@Test
	@Order(1)
    public void testAddTest() throws Exception {

		user = new Users("XYZ","XYZ@Zgmail.com",5);
		user.setId(1L);
		
		when(userService.addUser(Mockito.any(Users.class))).thenReturn(user);  // Note in this case user object is he parameter only then mock method is called but on controller there is another user object there method is not mocked. Mockito uses the equals for argument matching, try using ArgumentMatchers.any for the save method.
		//converting java object to json as MockMvc only takes json and not java object
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(user);
		this.mockMvc.perform(post("/testAddUser").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andDo(print());
		
		/*ObjectMapper mapper = new ObjectMapper();
		
		Mockito.when(userService.addUser(user)).thenReturn(user);
		
		System.out.println(userService.addUser(user).getEmailId());
		
        mockMvc.perform(MockMvcRequestBuilders.post("/testAddUser")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(mapper.writeValueAsString(user)) 
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());*/
                /*.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("abc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailId").value("abc@abc.com"))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.balance").value("5000.0"));*/
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("abc")) // If return json array then use $.[0] ie 1st element of array
        		//.andExpect(MockMvcResultMatchers.jsonPath("$[0].balance").value("5000.0"));
        
    }
}
