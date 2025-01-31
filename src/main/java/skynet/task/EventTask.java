package skynet.task;

/**
 * Create task for events with start and end times.
 */
public class EventTask extends Task {

    private final String start;
    private final String end;

    /**
     * Constructor for event tasks.
     * @param name Name of task.
     * @param start Starting time of task.
     * @param end Ending time of tasks.
     */
    public EventTask(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;

    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + String.format(" (from: %s to: %s)", start, end);
    }
}
