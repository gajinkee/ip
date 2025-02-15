package skynet.task;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * List of Tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Initialises task list with an array of tasks.
     *
     * @param arr Array of tasks.
     */
    public TaskList(ArrayList<Task> arr) {
        this.tasks = new ArrayList<>(arr);
    }

    /**
     * Initialises empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to task list.
     *
     * @param task New task to add.
     */
    public void add(Task task) {
        if (isRepeatedTask(task)) {
            return;
        }
        this.tasks.add(task);
    }

    /**
     * Removes task at the given index.
     *
     * @param index index of task to remove
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Get task at the given index.
     *
     * @param index index of task to get
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Find size of task list.
     *
     * @return int for the size of task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets the whole task list.
     *
     * @return ArrayList<> of the task list is returned.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Finds tasks related to the input string.
     *
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

    /**
     * Checks for repeated tasks in the task list.
     *
     * @param task Task to check.
     * @return A boolean True if Task is repeated.
     */
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
