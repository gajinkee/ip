package skynet.task;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Initialise task list with an array of tasks.
     * @param arr Array of tasks.
     */
    public TaskList(ArrayList<Task> arr) {
        this.tasks = new ArrayList<>(arr);
    }

    /**
     * Initialise empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add task to task list.
     * @param task New task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Remove task at the given index.
     * @param index index of task to remove
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Get task at the given index.
     * @param index index of task to get
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Find size of task list.
     * @return Int for the size of task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets the whole task list
     * @return ArrayList<Task> of the task list is returned.
     */
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
