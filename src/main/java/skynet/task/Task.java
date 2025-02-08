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

    @Override
    public String toString() {
        return "["
                + (this.done ? "X" : " ")
                + "] "
                + this.name;
    }

}
