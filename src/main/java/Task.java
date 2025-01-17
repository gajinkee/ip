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

    public Task markTask() {
        this.done = true;
        return this;
    }

    public Task unMarkTask() {
        this.done = false;
        return this;
    }

    @Override
    public String toString() {
        return "[" + (this.done? "X" : " ")  + "] " +this.name;
    }

}
