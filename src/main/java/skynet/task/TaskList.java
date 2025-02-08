package skynet.task;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * List of Tasks.
 */
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
        if (isRepeatedTask(task)) {
            return;
        }
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
     * @return int for the size of task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets the whole task list.
     * @return ArrayList<> of the task list is returned.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Find tasks related to the input string.
     * @param input String to find related tasks in the list.
     * @return TaskList of related Tasks
     */
    public TaskList findRelatedTasks(String input) {
        ArrayList<Task> results = new ArrayList<>(this.tasks
                .stream()
                .filter(task -> task.getName().toLowerCase().contains(input))
                .toList());

        return new TaskList(results);
    }

    private boolean isRepeatedTask(Task task) {
        return this.tasks.stream()
                .map(t -> t.compareTaskName(task))
                .anyMatch(val -> val == 0);
    }

    @Override
    public String toString() {
        return IntStream.range(0, this.tasks.size())
                .mapToObj(x -> x + "." + this.tasks.get(x))
                .reduce("", (x, y) -> x + y + "\n").strip();
    }
}
