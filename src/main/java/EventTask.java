public class EventTask extends Task{

    private final String start;
    private final String end;

    EventTask(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;

    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)",start,end);
    }
}
