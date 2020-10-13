package hpst.spring.tdd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hpst.spring.tdd.dto.UserDto;
import hpst.spring.tdd.entity.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
	
	@Query("select new com.hpst.testcontainer.demo.UserDto(u.id,u.name) from User u")
	public List<UserDto> getUsers();
}