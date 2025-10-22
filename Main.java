import java.util.Scanner;

public class Main {
    private static final int MAX_USERS = 10; 
    private static final User[] users = new User[MAX_USERS];
    private static int userCount = 0;

    public static void main(String[] args) {
        addUser(new User("Valeriia"));
        addUser(new User("Andrii"));
        addUser(new User("Sofia"));
        findUser("Valeriia").addTask("Finish database design assignment");
        findUser("Valeriia").addTask("Prepare candle order for Yes.CNDL client");
        findUser("Valeriia").addTask("Upload new floral photos to portfolio website");
        findUser("Valeriia").addTask("Go to tennis training at 6:30 PM");

        runMenu();
    }

    private static void runMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== To-Do List Manager ===");
            System.out.println("1) Add user");
            System.out.println("2) Add task to a user");
            System.out.println("3) Mark task completed (by index)");
            System.out.println("4) Mark task completed (by description)");
            System.out.println("5) View tasks for a user");
            System.out.println("6) View ALL users and tasks");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Enter unique user name: ");
                    String name = sc.nextLine().trim();
                    addUser(new User(name));
                    break;

                case "2": {
                    User u = promptUser(sc);
                    if (u != null) {
                        System.out.print("Task description: ");
                        String desc = sc.nextLine();
                        try {
                            u.addTask(desc);
                            System.out.println("Added.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;
                }

                case "3": { 
                    User u = promptUser(sc);
                    if (u != null) {
                        u.printTasks();
                        System.out.print("Enter task index to mark completed: ");
                        String idxStr = sc.nextLine().trim();
                        try {
                            int idx = Integer.parseInt(idxStr);
                            boolean ok = u.markTaskCompleted(idx);
                            System.out.println(ok ? "Marked as completed." : "Invalid index.");
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a number.");
                        }
                    }
                    break;
                }

                case "4": { 
                    User u = promptUser(sc);
                    if (u != null) {
                        System.out.print("Enter EXACT task description to mark completed: ");
                        String desc = sc.nextLine();
                        boolean ok = u.markTaskCompleted(desc);
                        System.out.println(ok ? "Marked as completed." : "Task not found.");
                    }
                    break;
                }

                case "5": {
                    User u = promptUser(sc);
                    if (u != null) {
                        u.printTasks();
                    }
                    break;
                }

                case "6":
                    printAllUsersAndTasks();
                    break;

                case "0":
                    System.out.println("Bye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static User promptUser(Scanner sc) {
        if (userCount == 0) {
            System.out.println("No users yet. Add a user first.");
            return null;
        }
        System.out.print("Enter user name: ");
        String name = sc.nextLine().trim();
        User u = findUser(name);
        if (u == null) {
            System.out.println("User not found.");
            return null;
        }
        return u;
    }

    private static void addUser(User user) {
        if (user == null) return;
        if (userCount >= MAX_USERS) {
            System.out.println("User list is full. Cannot add: " + user.getName());
            return;
        }
        if (findUser(user.getName()) != null) {
            System.out.println("User '" + user.getName() + "' already exists. Skipping.");
            return;
        }
        users[userCount++] = user;
        System.out.println("Added user: " + user.getName());
    }

    private static User findUser(String name) {
        if (name == null) return null;
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equalsIgnoreCase(name.trim())) {
                return users[i];
            }
        }
        return null;
    }

    private static void printAllUsersAndTasks() {
        System.out.println("\n=== To-Do Lists ===");
        if (userCount == 0) {
            System.out.println("(no users)");
            return;
        }
        for (int i = 0; i < userCount; i++) {
            users[i].printTasks();
            System.out.println();
        }
    }
}

