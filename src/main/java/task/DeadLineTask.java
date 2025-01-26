package task;

public class DeadLineTask extends Task {
    private final String deadline;

    public DeadLineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline);
    }
}
