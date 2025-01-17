import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Skynet {
    public static void main(String[] args) {
        String logo = """
                  ______   __                               _   \s
                .' ____ \\ [  |  _                          / |_ \s
                | (___ \\_| | | / ]   _   __  _ .--.  .---.`| |-'\s
                 _.____`.  | '' <   [ \\ [  ][ `.-. |/ /__\\\\| |  \s
                | \\____) | | |`\\ \\   \\ '/ /  | | | || \\__.,| |, \s
                 \\______.'[__|  \\_][\\_:  /  [___||__]'.__.'\\__/ \s
                                    \\__.'                       \s""";
        System.out.println(logo + "\nHello! Welcome to skynet\nWhat can i do for you?\n"+ "-".repeat(20));

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskArray = new ArrayList<>();
        String userInput = scanner.nextLine();
        System.out.println("here?");
        while (!userInput.equals("bye")) {
            String[] userInputs = userInput.split(" ");
            int index;
            switch (userInputs[0]){
                case "list":
                    IntStream.range(0,taskArray.size())
                            .forEach(x -> System.out.println( x + "." + taskArray.get(x)));
                    break;
                case "mark":
                    index = Integer.parseInt(userInputs[1]);
                    taskArray.get(index).markTask();
                    System.out.println("Nice! Ive marked this task as done:\n" + taskArray.get(index));
                    break;
                case "unmark":
                    index = Integer.parseInt(userInputs[1]);
                    taskArray.get(index).unMarkTask();
                    System.out.println("OK, Ive marked this task as not done:\n" + taskArray.get(index));
                    break;
                default:
                    taskArray.add(new Task(userInput));
                    System.out.println("Added: " + userInput);
            }

            userInput = scanner.nextLine();
        }

        System.out.println("Good Bye! See you again soon.");
    }
}
