/**
 * 
 */
package hpst.spring.tdd.service.testcontainer;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import hpst.spring.tdd.entity.User;
import hpst.spring.tdd.repository.UserJpaRepository;


/**
 * 
 * Integration Test Using Test Containers
 * @author harpal
 *
 */
@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@DataJpaTest(showSql = true)

@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestMySqlTestContainerIT {

    // will be shared between test methods
    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>();


   @BeforeAll
   public void startMySqlContainer() {
		MY_SQL_CONTAINER.start();
   }
    
    @Test
    @DisplayName("isMsqlContainerRunning")
    void isMsqlContainerRunning() {
    	assertTrue(MY_SQL_CONTAINER.isRunning());
    }
    

	@Autowired
	private UserJpaRepository userRepo;

	@Test
	@DisplayName("Insert User Test")
	void testInsertUser() {
		User savedUser = userRepo.save(new User(1l, "Harpal"));
		assertEquals(savedUser.getName(), "Harpal");
	}
    
    @After
    void stopContainer() {
    	MY_SQL_CONTAINER.stop();
    }
}