package tn.esprit.spring.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IUserService;
import tn.esprit.spring.services.UserServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceImplTest {
	
	@Autowired
	IUserService us;

	
	public void testRetrieveAllUsers() {
	List<User> listUsers = us.retrieveAllUsers();
	Assertions.assertEquals(22, listUsers.size());
	}
	
	
	public void testAddUser() throws ParseException, java.text.ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2015-03-23");
		User u = new User ("maysa", "maysa", d, Role.STUDENT);
		User userAdded = us.addUser(u);
		Assertions.assertEquals(u.getLastName(), userAdded.getLastName());
	}
		
	
	public void testUpdateUser() throws ParseException, java.text.ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2015-03-15");
		User u = new User (7L, "samar","samar", d, Role.INGENIEUR);
		User userUpdated = us.addUser(u);
		Assertions.assertEquals(u.getLastName(), userUpdated.getLastName());
		
		
	}
	
	public void testDeleteUser(){
		us.deleteUser("7");
		Assertions.assertNull(us.retrieveUser("7"));
		
	}
	
	
	public void testRetrieveUser(){
		User userRetrieved = us.retrieveUser("7");
		Assertions.assertEquals(7L, userRetrieved.getId().longValue());
		
	}
	private static final Logger L = LogManager.getLogger(UserServiceImpl.class);
	
	@Test
	public void testAll(){
		try{
			L.info("In testAll()");
			testRetrieveAllUsers();
			testAddUser();
			testUpdateUser();
			testDeleteUser();
			testRetrieveUser();
			L.info("Out of testAll()");
		}catch(Exception e){
			L.error("Out of testAll()with error:" +e);
		}
		
	}
	

}
