import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Arrays;

public class Skynet {
    public static void main(String[] args) {
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

        ArrayList<Task> taskArray = new ArrayList<>();
        handleCommand(taskArray);


    }

    private static void handleCommand(ArrayList<Task> taskArray) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String inputLine = scanner.nextLine();
            if(inputLine.equals("bye")) {
                break;
            }
            try {
                String[] eventString = inputLine.split("/")[0].split(" ");
                UserInput caseType = UserInput.fromString(eventString[0]);

                int index;
                String taskName, date;
                Task newTask;
                switch (caseType) {
                case LIST:
                    IntStream.range(0, taskArray.size())
                            .forEach(x -> System.out.println(x + "." + taskArray.get(x)));
                    break;

                case MARK:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the task index to mark.");
                    }
                    index = Integer.parseInt(eventString[1]);
                    taskArray.get(index).markTask();
                    System.out.println("Nice! Ive marked this task as done:\n" + taskArray.get(index));
                    break;

                case UNMARK:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the task index to unmark.");
                    }
                    index = Integer.parseInt(eventString[1]);
                    taskArray.get(index).unMarkTask();
                    System.out.println("OK, Ive marked this task as not done:\n" + taskArray.get(index));
                    break;

                case TODO:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the task name.");
                    }
                    //taskName = eventString[1];
                    taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                    newTask = new ToDoTask(taskName);
                    taskArray.add(newTask);
                    System.out.printf("Added: %s\nYou now have %s tasks%n", newTask, taskArray.size());
                    break;

                case DEADLINE:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the task name and deadline." +
                                "\nFormat: task taskName /by time");
                    }
                    taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                    date = inputLine.split("/by")[1].strip();
                    newTask = new DeadLineTask(taskName, date);
                    taskArray.add(newTask);
                    System.out.printf("Added: %s\nYou now have %s tasks%n", newTask, taskArray.size());
                    break;

                case EVENT:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the task name and event duration." +
                                "\nFormat: task taskName /from time /to time");
                    }

                    taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                    String[] duration = (inputLine.split("/from")[1]).split("/to");
                    String startDate = duration[0].strip();
                    String endDate = duration[1].strip();

                    newTask = new EventTask(taskName, startDate, endDate);
                    taskArray.add(newTask);
                    System.out.printf("Added: %s\nYou now have %s tasks%n", newTask, taskArray.size());
                    break;

                case DELETE:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the task index to delete.");
                    }
                    index = Integer.parseInt(eventString[1]);
                    System.out.println("OK, Ive deleted this task:\n" + taskArray.get(index));
                    taskArray.remove(index);
                    break;

                default:
                    System.out.println("Sorry I dont understand: " + inputLine);
                }
            } catch (MissingArgumentException e) {
                System.out.println("Error:\n" + e.getMessage());
            } catch (Exception e) {
                System.out.println("Command Error:\n" + e.getMessage());
            }
        }
        System.out.println("Good Bye! See you again soon.");
        scanner.close();
    }

}
