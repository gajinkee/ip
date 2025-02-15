package skynet.task;

/**
 * General Task class to be inherited by derivative task types.
 */
public class Task {
    private boolean isDone;
    private final String name;

    Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * Gets task name.
     *
     * @return String of the task name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Marks task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void unMarkTask() {
        this.isDone = false;
    }

    /**
     * Compares task name.
     *
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
                + (this.isDone ? "X" : " ")
                + "] "
                + this.name;
    }

}
