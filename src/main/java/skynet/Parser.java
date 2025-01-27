package skynet;

import skynet.task.DeadLineTask;
import skynet.task.EventTask;
import skynet.task.Task;
import skynet.task.ToDoTask;
import skynet.task.TaskList;

import java.util.Arrays;
import skynet.exceptions.MissingArgumentException;
import skynet.ui.UI;
import skynet.ui.UserInput;


public class Parser {
    public static void handleCommand(TaskList taskArray, UI ui) {
        while (true) {
            String inputLine = ui.scanNextLine();
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
                    System.out.println(taskArray.toString());
                    break;

                case MARK:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task index to mark.");
                    }
                    index = Integer.parseInt(eventString[1]);
                    taskArray.get(index).markTask();
                    ui.printMark(taskArray,index);
                    break;

                case UNMARK:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task index to unmark.");
                    }
                    index = Integer.parseInt(eventString[1]);
                    taskArray.get(index).unMarkTask();
                    ui.printUnMark(taskArray,index);
                    break;

                case TODO:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task name.");
                    }
                    taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                    newTask = new ToDoTask(taskName);
                    taskArray.add(newTask);
                    ui.printTaskAdded(newTask, taskArray.size());
                    break;

                case DEADLINE:
                    if (eventString.length < 2 | inputLine.split("/by").length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task name and deadline." +
                                "\nFormat: skynet.task taskName /by time");
                    }
                    taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                    date = inputLine.split("/by")[1].strip();
                    newTask = new DeadLineTask(taskName, date);
                    taskArray.add(newTask);
                    ui.printTaskAdded(newTask, taskArray.size());
                    break;

                case EVENT:
                    if (eventString.length < 2 |
                            inputLine.split("/from").length < 2)
                            {
                        throw new MissingArgumentException("Please specify the skynet.task name and event duration." +
                                "\nFormat: skynet.task taskName /from time /to time");
                    }

                    if ( inputLine.split("/from")[1].split("/to").length < 2) {
                    throw new MissingArgumentException("Please specify the skynet.task name and event duration." +
                            "\nFormat: skynet.task taskName /from time /to time");
                    }

                    taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                    String[] duration = inputLine.split("/from")[1].split("/to");
                    String startDate = duration[0].strip();
                    String endDate = duration[1].strip();

                    newTask = new EventTask(taskName, startDate, endDate);
                    taskArray.add(newTask);
                    ui.printTaskAdded(newTask, taskArray.size());
                    break;

                case DELETE:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task index to delete.");
                    }
                    index = Integer.parseInt(eventString[1]);
                    ui.printDeletedTask(taskArray.get(index));
                    taskArray.remove(index);
                    break;

                default:
                    ui.printFailureToParseInput(inputLine);
                }
            } catch (MissingArgumentException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Command Error:\n" + e.getMessage());
            }
        }
        ui.printGoodBye();
        ui.closeScanner();
    }
}
