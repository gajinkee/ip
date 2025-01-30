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

    Task(String name, boolean done) {
        this.done = done;
        this.name = name;
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
     * Checks if task is done.
     * @return Returns True if task is done.
     */
    public boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.name;
    }

}
