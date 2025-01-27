package skynet.task;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> arr) {
        this.tasks = new ArrayList<Task>(arr);
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    @Override
    public String toString() {
        return  IntStream.range(0, this.tasks.size())
                .mapToObj(x -> x + "." + this.tasks.get(x))
                .reduce("", (x,y) -> x+y+"\n").strip();
    }
}
