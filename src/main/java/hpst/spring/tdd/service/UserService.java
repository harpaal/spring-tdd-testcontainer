/**
 * 
 */
package hpst.spring.tdd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hpst.spring.tdd.dto.UserDto;
import hpst.spring.tdd.entity.User;
import hpst.spring.tdd.repository.UserJpaRepository;
import lombok.ToString;

/**
 * @author harpal
 *
 */
@Service
@ToString
public class UserService {

	@Autowired
	private UserJpaRepository userRepo;
	
	@Value("user.prefix")
	private String userPrefix;
	
	@Value("random.jokes.generator.api")
	private String randomJokesApi;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public List<UserDto> getUserList(){
		return userRepo.getUsers();
	}


	public String getRandomJokes() {
		return this.restTemplate.getForObject(this.randomJokesApi, String.class);
	}
	/**
	 * @param user 
	 * @return
	 */
	public User  saveUser(String name) {
		User user = new User(1l, userPrefix+name);
		 return userRepo.save(user);
	}
}
