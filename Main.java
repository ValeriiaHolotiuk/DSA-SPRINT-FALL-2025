public class Main {
    private static final int MAX_USERS = 10;
    private static final User[] users = new User[MAX_USERS];
    private static int userCount = 0;

    public static void main(String[] args) {

        addUser(new User("Valeriia"));
        addUser(new User("Andrii"));
        addUser(new User("Sofia"));


        User valeriia = findUser("Valeriia");
        if (valeriia != null) {
            valeriia.addTask("Finish database design assignment");
            valeriia.addTask("Prepare candle order for client");
            valeriia.addTask("Upload new floral photos to portfolio website");
            valeriia.addTask("Go to tennis training at 6:30 PM");
        }

        User andrii= findUser("Andrii");
        if (andrii!= null) {
            andrii.addTask("Fix login bug in React app");
            andrii.addTask("Write documentation for API endpoints");
            andrii.addTask("Evening shift at Jag hotel");
        }

        
        User sofia = findUser("Sofia");
        if (sofia != null) {
            sofia.addTask("Schedule meeting with design team");
            sofia.addTask("Buy flowers for weekend event");
            sofia.addTask("Review UI/UX project submissions");
        }

        if (valeriia != null) {
            valeriia.markTaskCompleted("Finish database design assignment");
            valeriia.markTaskCompleted(3); 
        }

        if (andrii != null) {
            andrii.markTaskCompleted("Pick up package from post office");
        }

        if (sofia != null) {
            sofia.markTaskCompleted("Buy flowers for weekend event");
        }

        printAllUsersAndTasks();
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
