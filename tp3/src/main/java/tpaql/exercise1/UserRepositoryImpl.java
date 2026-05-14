package tpaql.exercise1;

import java.sql.*;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "email VARCHAR(255))");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create users table", e);
        }
    }

    @Override
    public User findUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT id, name, email FROM users WHERE id = ?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find user by id", e);
        }
    }

    @Override
    public void saveUser(User user) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO users (id, name, email) VALUES (?, ?, ?)")) {
            ps.setLong(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Override
    public void deleteUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete user", e);
        }
    }
}
