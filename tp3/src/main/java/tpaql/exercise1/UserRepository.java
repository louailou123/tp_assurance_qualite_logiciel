package tpaql.exercise1;

public interface UserRepository {
    User findUserById(long id);
    void saveUser(User user);
    void deleteUserById(long id);
}
