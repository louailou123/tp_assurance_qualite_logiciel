package tpaql.exercise2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepositoryImpl implements TaskRepository {

    private final Connection connection;

    public TaskRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS tasks (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "description TEXT, " +
                    "completed BOOLEAN DEFAULT FALSE)");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create tasks table", e);
        }
    }

    @Override
    public Task save(Task task) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO tasks (name, description, completed) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setBoolean(3, task.isCompleted());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getLong(1));
            }
            return task;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save task", e);
        }
    }

    @Override
    public Optional<Task> findById(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT id, name, description, completed FROM tasks WHERE id = ?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find task by id", e);
        }
    }

    @Override
    public List<Task> findAll() {
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT id, name, description, completed FROM tasks");
            List<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                tasks.add(mapRow(rs));
            }
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find all tasks", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM tasks WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete task", e);
        }
    }

    @Override
    public Task update(Task task) {
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE tasks SET name = ?, description = ?, completed = ? WHERE id = ?")) {
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setBoolean(3, task.isCompleted());
            ps.setLong(4, task.getId());
            ps.executeUpdate();
            return task;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update task", e);
        }
    }

    private Task mapRow(ResultSet rs) throws SQLException {
        return new Task(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getBoolean("completed")
        );
    }
}
