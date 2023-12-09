// TaskDAO.java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/task_management";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // Create
    public void createTask(Task task) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO tasks (taskName, taskDescription, priority, deadline) VALUES (?,?, ?, ?)"
             )) {
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setString(2, task.getTaskDescription());
            preparedStatement.setInt(3, task.getPriority());
            preparedStatement.setDate(4, new java.sql.Date(task.getDeadline().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks")) {

            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getInt("taskId"));
                task.setTaskName(resultSet.getString("taskName"));
                task.setTaskDescription(resultSet.getString("taskDescription"));
                task.setPriority(resultSet.getInt("priority"));
                task.setDeadline(resultSet.getDate("deadline"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Update
    public void updateTask(Task task) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE tasks SET taskName=?, taskDescription=?, priority=?, deadline=? WHERE taskId=?"
             )) {
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setString(2, task.getTaskDescription());
            preparedStatement.setInt(3, task.getPriority());
            preparedStatement.setDate(4, new java.sql.Date(task.getDeadline().getTime()));
            preparedStatement.setInt(5, task.getTaskId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteTask(int taskId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM tasks WHERE taskId=?"
             )) {
            preparedStatement.setInt(1, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

