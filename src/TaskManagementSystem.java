import java.util.Date;
import java.util.List;

public class TaskManagementSystem {
    public static void main(String[] args) {
        TaskDAO taskDAO = new TaskDAO();

        // Example: Creating a task
        Task newTask = new Task();
        newTask.setTaskName("Complete Assignment");
        newTask.setTaskDescription("Enhance Task Management System");
        newTask.setPriority(1);
        newTask.setDeadline(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)); // Set deadline to 7 days from now

        taskDAO.createTask(newTask);

        // Example: Reading all tasks
        List<Task> tasks = taskDAO.getAllTasks();
        for (Task task : tasks) {
            System.out.println(task.getTaskName() + " - " + task.getTaskDescription());
        }

        // Example: Updating a task
        Task taskToUpdate = tasks.get(0);
        taskToUpdate.setTaskName("Updated Task Name");
        taskDAO.updateTask(taskToUpdate);

        // Example: Deleting a task
        int taskIdToDelete = tasks.get(1).getTaskId();
        taskDAO.deleteTask(taskIdToDelete);
    }
}
