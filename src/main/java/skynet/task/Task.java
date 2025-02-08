package skynet.task;

/**
 * General Task class to be inherited by derivative task types.
 */
public class Task {
    private boolean done;
    private final String name;

    Task(String name) {
        this.done = false;
        this.name = name;
    }

    /**
     * Getter for task name.
     * @return String of the task name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Mark task as done.
     */
    public void markTask() {
        this.done = true;
    }

    /**
     * Mark task as not done.
     */
    public void unMarkTask() {
        this.done = false;
    }

    /**
     * comparator for task name. Returns 0 if equal.
     * @param task Task to compare with.
     * @return integer 0 if equal else 1 or -1.
     */
    public int compareTaskName(Task task) {
        return this.name
                .compareToIgnoreCase(task.name);
    }

    @Override
    public String toString() {
        return "["
                + (this.done ? "X" : " ")
                + "] "
                + this.name;
    }

}
