import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Skynet {
    public static void main(String[] args) {
        String logo = "  ______   __                               _    \n" +
                ".' ____ \\ [  |  _                          / |_  \n" +
                "| (___ \\_| | | / ]   _   __  _ .--.  .---.`| |-' \n" +
                " _.____`.  | '' <   [ \\ [  ][ `.-. |/ /__\\\\| |   \n" +
                "| \\____) | | |`\\ \\   \\ '/ /  | | | || \\__.,| |,  \n" +
                " \\______.'[__|  \\_][\\_:  /  [___||__]'.__.'\\__/  \n" +
                "                    \\__.'                        ";
        System.out.println(logo + "\nHello! Welcome to skynet\nWhat can i do for you?\n"+ "-".repeat(20));

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> logs = new ArrayList<String>();
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                IntStream.range(0,logs.size())
                        .forEach(x -> System.out.println( x + ". " + logs.get(x)));
            }
            else {
                logs.add(userInput);
                System.out.println("Added: " + userInput);
            }
            userInput = scanner.nextLine();
        }

        System.out.println("Good Bye! See you again soon.");
    }
}
