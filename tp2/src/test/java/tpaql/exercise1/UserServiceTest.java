package tpaql.exercise1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void shouldReturnUserWhenIdExists() {
        User expectedUser = new User(1L, "Ali", "ali@mail.com");
        when(userRepository.findUserById(1L)).thenReturn(expectedUser);

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Ali", result.getName());
        assertEquals("ali@mail.com", result.getEmail());

        verify(userRepository, times(1)).findUserById(1L);
    }
}