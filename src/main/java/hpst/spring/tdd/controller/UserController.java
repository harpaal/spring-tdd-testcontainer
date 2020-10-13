/**
 * 
 */
package hpst.spring.tdd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import hpst.spring.tdd.dto.UserDto;
import hpst.spring.tdd.service.UserService;



/**
 * @author harpal
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/get-users")
	public List<UserDto> getUsers(){
		return userService.getUserList();
	}
	
	
	@GetMapping("/get-random-user")
	public String getRandomUser(){
		return userService.getRandomJokes();
	}
	
	
	@PutMapping("/add-users")
	public void addUsers(String name){
		 userService.saveUser(name);
	}
	
	


}