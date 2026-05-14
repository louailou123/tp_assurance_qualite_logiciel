package tpaql.exercise1;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class UserServiceIntegrationTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    private Connection connection;
    private UserRepositoryImpl userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection(
                mysql.getJdbcUrl(),
                mysql.getUsername(),
                mysql.getPassword()
        );
        userRepository = new UserRepositoryImpl(connection);
        userRepository.createTable();
        userService = new UserService(userRepository);

        // Clean table before each test
        connection.createStatement().execute("DELETE FROM users");
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    @DisplayName("Should save and retrieve a user from MySQL")
    void shouldSaveAndRetrieveUser() {
        User user = new User(1L, "Ali", "ali@mail.com");
        userService.createUser(user);

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Ali", result.getName());
        assertEquals("ali@mail.com", result.getEmail());
    }

    @Test
    @DisplayName("Should return null when user ID does not exist")
    void shouldReturnNullWhenUserNotFound() {
        User result = userService.getUserById(999L);

        assertNull(result);
    }

    @Test
    @DisplayName("Should delete a user from MySQL")
    void shouldDeleteUser() {
        User user = new User(2L, "Sara", "sara@mail.com");
        userService.createUser(user);

        assertNotNull(userService.getUserById(2L));

        userService.deleteUser(2L);

        assertNull(userService.getUserById(2L));
    }

    @Test
    @DisplayName("Should throw exception when creating null user")
    void shouldThrowExceptionWhenUserIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.createUser(null)
        );

        assertEquals("User must not be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should save multiple users and retrieve each correctly")
    void shouldSaveMultipleUsersAndRetrieveEach() {
        User user1 = new User(10L, "Ahmed", "ahmed@mail.com");
        User user2 = new User(20L, "Fatima", "fatima@mail.com");

        userService.createUser(user1);
        userService.createUser(user2);

        User result1 = userService.getUserById(10L);
        User result2 = userService.getUserById(20L);

        assertNotNull(result1);
        assertEquals("Ahmed", result1.getName());

        assertNotNull(result2);
        assertEquals("Fatima", result2.getName());
    }
}
