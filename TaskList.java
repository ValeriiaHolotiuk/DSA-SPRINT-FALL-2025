public class TaskList {
    private TaskNode head;
    private int size;

    public TaskList() {
        head = null;
        size = 0;
    }

    // Add a new task to the END of the list
    public void addTask(String description) {
        TaskNode node = new TaskNode(new Task(description));
        if (head == null) {
            head = node;
        } else {
            TaskNode cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = node;
        }
        size++;
    }

    // Mark task completed by description (case-insensitive)
    public boolean markTaskCompleted(String description) {
        if (description == null) return false;
        String target = description.trim().toLowerCase();
        TaskNode cur = head;
        while (cur != null) {
            if (cur.data.getDescription().toLowerCase().equals(target)) {
                cur.data.markCompleted();
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // Alternative: mark by zero-based index (handy if descriptions repeat)
    public boolean markTaskCompleted(int index) {
        if (index < 0 || index >= size) return false;
        TaskNode cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        cur.data.markCompleted();
        return true;
    }

    // Print all tasks with their indices
    public void printAllTasks() {
        if (head == null) {
            System.out.println("   (no tasks)");
            return;
        }
        TaskNode cur = head;
        int i = 0;
        while (cur != null) {
            System.out.println("   " + i + ". " + cur.data);
            cur = cur.next;
            i++;
        }
    }

    public int size() {
        return size;
    }
}
