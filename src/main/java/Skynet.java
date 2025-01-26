import storage.StorageHandler;
import task.DeadLineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Arrays;

public class Skynet {
    public static void main(String[] args) {
        try {
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


            ArrayList<Task> taskArray = StorageHandler.load("data.txt");
            CommandHandler.handleCommand(taskArray);
            StorageHandler.save("data.txt", taskArray);

        } catch (Exception e) {
            System.out.println("Error in main:\n" + e.getMessage());
        }

    }
}
