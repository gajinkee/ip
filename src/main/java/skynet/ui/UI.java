package skynet.ui;

import skynet.task.Task;
import skynet.task.TaskList;

import java.util.Scanner;

public class UI {

    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String scanNextLine() {
        return this.scanner.nextLine();
    }

    public void closeScanner() {
        this.scanner.close();
    }

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

    public void printMark(TaskList taskArray, int index) {
        System.out.println("Nice! Ive marked this skynet.task as done:\n" + taskArray.get(index));
    }

    public void printUnMark(TaskList taskArray, int index) {
        System.out.println("OK, Ive marked this skynet.task as not done:\n" + taskArray.get(index));
    }

    public void printTaskAdded(Task newTask, int numOfTasks) {
        System.out.printf("Added: %s\nYou now have %s tasks%n", newTask, numOfTasks);
    }

    public void printDeletedTask(Task task) {
        System.out.println("OK, Ive deleted this skynet.task:\n" + task);
    }

    public void printGoodBye() {
        System.out.println("Good Bye! See you again soon.");
    }

    public void printFailureToParseInput(String inputLine) {
        System.out.println("Sorry I dont understand: " + inputLine);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
