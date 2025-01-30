package skynet.ui;

import skynet.task.Task;
import skynet.task.TaskList;

import java.util.Scanner;

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
     * @return String input line.
     */
    public String scanNextLine() {
        return this.scanner.nextLine();
    }

    /**
     * Close the UI scanner
     */
    public void closeScanner() {
        this.scanner.close();
    }

    /**
     * Display welcome message
     */
    public void showWelcome() {
        String logo = """
                                      ______   __                               _
                                    .' ____ \\ [  |  _                          / |_
                                    | (___ \\_| | | / ]   _   __  _ .--.  .---.`| |-'
                                     _.____`.  | '' <   [ \\ [  ][ `.-. |/ /__\\\\| |
                                    | \\____) | | |`\\ \\   \\ '/ /  | | | || \\__.,| |,
                                     \\______.'[__|  \\_][\\_:  /  [___||__]'.__.'\\__/
                                                        \\__.'
                    """;

        System.out.println(logo + "\nHello! Welcome to skynet\nWhat can i do for you?\n" + "-".repeat(20));
    }

    /**
     * Prints the marked task.
     * @param taskArray Array of tasks.
     * @param index Index to mark.
     */
    public void printMark(TaskList taskArray, int index) {
        System.out.println("Nice! Ive marked this skynet.task as done:\n" + taskArray.get(index));
    }

    /**
     * Prints the unmarked task.
     * @param taskArray Array of tasks.
     * @param index Index to unmark.
     */
    public void printUnMark(TaskList taskArray, int index) {
        System.out.println("OK, Ive marked this skynet.task as not done:\n" + taskArray.get(index));
    }

    /**
     * Print added task.
     * @param newTask Task added.
     * @param numOfTasks Total tasks in list.
     */
    public void printTaskAdded(Task newTask, int numOfTasks) {
        System.out.printf("Added: %s\nYou now have %s tasks%n", newTask, numOfTasks);
    }

    /**
     * Print deleted task.
     * @param task Task deleted.
     */
    public void printDeletedTask(Task task) {
        System.out.println("OK, Ive deleted this skynet.task:\n" + task);
    }

    /**
     * Prints goodbye message.
     */
    public void printGoodBye() {
        System.out.println("Good Bye! See you again soon.");
    }

    /**
     * Prints input error.
     * @param inputLine Input that failed to parse.
     */
    public void printFailureToParseInput(String inputLine) {
        System.out.println("Sorry I dont understand: " + inputLine);
    }

    /**
     * Error messages to show.
     * @param errorMessage Current error thrown.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
