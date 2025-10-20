public class User {
    private final String name;
    private final TaskList tasks;

    public User(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name cannot be empty.");
        }
        this.name = name.trim();
        this.tasks = new TaskList();
    }

    public String getName() {
        return name;
    }

    public void addTask(String description) {
        tasks.addTask(description);
    }

    public boolean markTaskCompleted(String description) {
        return tasks.markTaskCompleted(description);
    }

    public boolean markTaskCompleted(int index) {
        return tasks.markTaskCompleted(index);
    }

    public void printTasks() {
        System.out.println("Tasks for " + name + ":");
        tasks.printAllTasks();
    }
}
