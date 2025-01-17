import java.util.Scanner;

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

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = scanner.nextLine();
        }

        System.out.println("Good Bye!");
    }
}
