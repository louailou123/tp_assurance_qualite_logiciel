package tpaql.exercise1;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        return userRepository.findUserById(id);
    }

    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
        userRepository.saveUser(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteUserById(id);
    }
}
