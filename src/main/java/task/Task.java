package task;

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

    public void markTask() {
        this.done = true;
    }

    public void unMarkTask() {
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.name;
    }

}
