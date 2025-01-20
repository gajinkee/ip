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

        while (!userInput.equals("bye")) {
            String[] userInputs = userInput.split("/");
            String[] eventString =  userInputs[0].split(" ");
            String caseType = eventString[0];

            int index;
            String taskName,date;
            Task newTask;
            switch (caseType){
                case "list":
                    IntStream.range(0,taskArray.size())
                            .forEach(x -> System.out.println( x + "." + taskArray.get(x)));
                    break;

                case "mark":
                    index = Integer.parseInt(eventString[1]);
                    taskArray.get(index).markTask();
                    System.out.println("Nice! Ive marked this task as done:\n" + taskArray.get(index));
                    break;

                case "unmark":
                    index = Integer.parseInt(eventString[1]);
                    taskArray.get(index).unMarkTask();
                    System.out.println("OK, Ive marked this task as not done:\n" + taskArray.get(index));
                    break;

                case "todo":
                    taskName = eventString[1];
                    newTask = new ToDoTask(taskName);
                    taskArray.add(newTask);
                    System.out.printf("Added: %s\nYou now have %s tasks%n", newTask,taskArray.size());
                    break;

                case "deadline":
                    taskName = eventString[1];
                    date = userInput.split("/by")[1].strip();
                    newTask = new DeadLineTask(taskName,date);
                    taskArray.add(newTask);
                    System.out.printf("Added: %s\nYou now have %s tasks%n",newTask,taskArray.size());
                    break;

                case "event":
                    taskName = eventString[1];
                    String[] duration = (userInput.split("/from")[1]).split("/to");
                    String startDate = duration[0].strip();
                    String endDate = duration[1].strip();

                    newTask = new EventTask(taskName,startDate,endDate);
                    taskArray.add(newTask);
                    System.out.printf("Added: %s\nYou now have %s tasks%n",newTask,taskArray.size());
                    break;
                default:
                    System.out.println("Sorry I dont understand " + userInput);
            }
            userInput = scanner.nextLine();
        }
        System.out.println("Good Bye! See you again soon.");
    }
}
