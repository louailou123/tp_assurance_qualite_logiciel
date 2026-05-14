package tpaql.exercise2;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class TaskServiceIntegrationTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("taskmanager")
            .withUsername("testuser")
            .withPassword("testpass");

    private Connection connection;
    private TaskRepositoryImpl taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection(
                mysql.getJdbcUrl(),
                mysql.getUsername(),
                mysql.getPassword()
        );
        taskRepository = new TaskRepositoryImpl(connection);
        taskRepository.createTable();
        taskService = new TaskService(taskRepository);

        // Clean table before each test
        connection.createStatement().execute("DELETE FROM tasks");
        connection.createStatement().execute("ALTER TABLE tasks AUTO_INCREMENT = 1");
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // ==================== Helper Method ====================

    private Task createAndSaveTask(String name, String description) {
        Task task = new Task(name, description);
        return taskService.saveTask(task);
    }

    // ==================== Tests: Create Task ====================

    @Test
    @DisplayName("Should create a task and persist it in MySQL")
    void testCreateTask() {
        Task task = createAndSaveTask("Tâche 1", "Description de la tâche 1");

        assertNotNull(task.getId());

        Optional<Task> retrievedTask = taskService.findTaskById(task.getId());

        assertTrue(retrievedTask.isPresent());
        assertEquals("Tâche 1", retrievedTask.get().getName());
        assertEquals("Description de la tâche 1", retrievedTask.get().getDescription());
        assertFalse(retrievedTask.get().isCompleted());
    }

    @Test
    @DisplayName("Should throw exception when creating null task")
    void testCreateNullTask() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> taskService.saveTask(null)
        );
        assertEquals("Task must not be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when task name is empty")
    void testCreateTaskWithEmptyName() {
        Task task = new Task("", "Some description");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> taskService.saveTask(task)
        );
        assertEquals("Task name must not be empty", exception.getMessage());
    }

    // ==================== Tests: Retrieve Task ====================

    @Test
    @DisplayName("Should retrieve an existing task by ID")
    void testGetTask() {
        Task savedTask = createAndSaveTask("Tâche Test", "Description test");

        Optional<Task> retrievedTask = taskService.findTaskById(savedTask.getId());

        assertTrue(retrievedTask.isPresent());
        assertEquals(savedTask.getId(), retrievedTask.get().getId());
        assertEquals("Tâche Test", retrievedTask.get().getName());
    }

    @Test
    @DisplayName("Should return empty when task ID does not exist")
    void testGetTaskNotFound() {
        Optional<Task> retrievedTask = taskService.findTaskById(999L);

        assertFalse(retrievedTask.isPresent());
    }

    // ==================== Tests: Find All Tasks ====================

    @Test
    @DisplayName("Should return all tasks from MySQL")
    void testFindAllTasks() {
        createAndSaveTask("Tâche 1", "Description 1");
        createAndSaveTask("Tâche 2", "Description 2");
        createAndSaveTask("Tâche 3", "Description 3");

        List<Task> tasks = taskService.findAllTasks();

        assertEquals(3, tasks.size());
    }

    @Test
    @DisplayName("Should return empty list when no tasks exist")
    void testFindAllTasksEmpty() {
        List<Task> tasks = taskService.findAllTasks();

        assertTrue(tasks.isEmpty());
    }

    // ==================== Tests: Delete Task ====================

    @Test
    @DisplayName("Should delete a task from MySQL")
    void testDeleteTask() {
        Task savedTask = createAndSaveTask("Tâche à supprimer", "Description");

        assertNotNull(savedTask.getId());
        assertTrue(taskService.findTaskById(savedTask.getId()).isPresent());

        taskService.deleteTask(savedTask.getId());

        assertFalse(taskService.findTaskById(savedTask.getId()).isPresent());
    }

    @Test
    @DisplayName("Should not throw when deleting non-existent task")
    void testDeleteNonExistentTask() {
        assertDoesNotThrow(() -> taskService.deleteTask(999L));
    }

    // ==================== Tests: Toggle Task Completed ====================

    @Test
    @DisplayName("Should toggle task completed status from false to true")
    void testToggleTaskCompleted() {
        Task savedTask = createAndSaveTask("Tâche toggle", "Description");

        assertFalse(savedTask.isCompleted());

        Task toggled = taskService.toggleTaskCompleted(savedTask.getId());

        assertTrue(toggled.isCompleted());

        Optional<Task> fromDb = taskService.findTaskById(savedTask.getId());
        assertTrue(fromDb.isPresent());
        assertTrue(fromDb.get().isCompleted());
    }

    @Test
    @DisplayName("Should toggle task completed status from true back to false")
    void testToggleTaskCompletedTwice() {
        Task savedTask = createAndSaveTask("Tâche double toggle", "Description");

        taskService.toggleTaskCompleted(savedTask.getId());
        Task toggled = taskService.toggleTaskCompleted(savedTask.getId());

        assertFalse(toggled.isCompleted());
    }

    @Test
    @DisplayName("Should throw exception when toggling non-existent task")
    void testToggleNonExistentTask() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> taskService.toggleTaskCompleted(999L)
        );
        assertTrue(exception.getMessage().contains("Task not found"));
    }
}
