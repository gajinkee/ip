package skynet.ui;

import java.util.Scanner;

import skynet.task.Task;
import skynet.task.TaskList;

/**
 * UI interface that handles user input and system output to user.
 * Wraps a Scanner and has System.out.println methods.
 */
public class UI {

    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Scans the next line of input.
     *
     * @return String input line.
     */
    public String scanNextLine() {
        return this.scanner.nextLine();
    }

    /**
     * Closes the UI scanner
     */
    public void closeScanner() {
        this.scanner.close();
    }

    /**
     * Displays welcome message
     */
    public String showWelcome() {
        String logo = """
                                  ______   __                               _
                                .' ____ \\ [  |  _                          / |_
                                | (___ \\_| | | / ]   _   __  _ .--.  .---.`| |-'
                                 _.____`.  | '' <   [ \\ [  ][ `.-. |/ /__\\\\| |
                                | \\____) | | |`\\ \\   \\ '/ /  | | | || \\__.,| |,
                                 \\______.'[__|  \\_][\\_:  /  [___||__]'.__.'\\__/
                                                    \\__.'
                """;

        String res = logo + "\nHello! Welcome to skynet\nWhat can i do for you?\n" + "-".repeat(20);
        System.out.println(res);
        return res;
    }

    /**
     * Prints the marked task.
     *
     * @param taskArray Array of tasks.
     * @param index     Index to mark.
     */
    public String printMark(TaskList taskArray, int index) {
        String res = "Nice! Ive marked this skynet.task as done:\n" + taskArray.get(index);
        System.out.println(res);
        return res;
    }

    /**
     * Prints the unmarked task.
     *
     * @param taskArray Array of tasks.
     * @param index     Index to unmark.
     */
    public String printUnMark(TaskList taskArray, int index) {
        String res = "OK, Ive marked this skynet.task as not done:\n" + taskArray.get(index);
        System.out.println(res);
        return res;
    }

    /**
     * Prints added task.
     *
     * @param newTask    Task added.
     * @param numOfTasks Total tasks in list.
     */
    public String printTaskAdded(Task newTask, int numOfTasks) {
        String res = String.format("Added: %s\nYou now have %s tasks", newTask, numOfTasks);
        System.out.println(res);
        return res;
    }

    /**
     * Prints deleted task.
     *
     * @param task Task deleted.
     */
    public String printDeletedTask(Task task) {
        String res = "OK, Ive deleted this skynet.task:\n" + task;
        System.out.println(res);
        return res;
    }

    /**
     * Prints goodbye message.
     */
    public String printGoodBye() {
        String res = "Good Bye! See you again soon.";
        System.out.println(res);
        return res;
    }

    /**
     * Prints input error.
     *
     * @param inputLine Input that failed to parse.
     */
    public String printFailureToParseInput(String inputLine) {
        String res = "Sorry I dont understand: " + inputLine;
        System.out.println(res);
        return res;
    }

    /**
     * Shows error messages.
     *
     * @param errorMessage Current error thrown.
     */
    public String showError(String errorMessage) {
        System.out.println(errorMessage);
        return errorMessage;
    }

    /**
     * Prints list of tasks.
     *
     * @param listString String of tasks.
     * @return String of tasks.
     */
    public String printList(String listString) {
        System.out.println(listString);
        return listString;
    }
}
