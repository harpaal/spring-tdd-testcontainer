package hpst.spring.tdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import hpst.spring.tdd.entity.User;
import hpst.spring.tdd.repository.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TestApplication implements CommandLineRunner{

	@Autowired
	UserJpaRepository userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User savedUser = userRepo.save(new User(1l, "Harpal"));
		log.info("savedUser"+savedUser.getName());
	}
	
	

}
