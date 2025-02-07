package skynet.ui;

import java.util.Arrays;

import skynet.exceptions.MissingArgumentException;
import skynet.task.DeadLineTask;
import skynet.task.EventTask;
import skynet.task.Task;
import skynet.task.TaskList;
import skynet.task.ToDoTask;

/**
 * Parser for handling and understanding user input
 */
public class Parser {

    /**
     * Handles user input
     *
     * @param taskList array of tasks used
     * @param ui       The interface for input and output
     */
    public static void handleCommand(TaskList taskList, UI ui) {
        while (true) {
            String inputLine = ui.scanNextLine();
            assert(inputLine != null);
            if (inputLine.equals("bye")) {
                break;
            }
            try {
                String[] eventString = inputLine.split("/")[0].split(" ");
                UserInput caseType = UserInput.fromString(eventString[0]);

                String taskName;
                String date;
                Task newTask;
                switch (caseType) {
                case LIST:
                    System.out.println(taskList.toString());
                    break;

                case MARK:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task index to mark.");
                    }
                    int indexToMark = Integer.parseInt(eventString[1]);
                    taskList.get(indexToMark).markTask();
                    ui.printMark(taskList, indexToMark);
                    break;

                case UNMARK:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task index to unmark.");
                    }
                    int indexToUnmark = Integer.parseInt(eventString[1]);
                    taskList.get(indexToUnmark).unMarkTask();
                    ui.printUnMark(taskList, indexToUnmark);
                    break;

                case TODO:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task name.");
                    }
                    taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                    newTask = new ToDoTask(taskName);
                    taskList.add(newTask);
                    ui.printTaskAdded(newTask, taskList.size());
                    break;

                case DEADLINE:
                    if (eventString.length < 2 | inputLine.split("/by").length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task name and deadline."
                                + "\nFormat: skynet.task taskName /by time");
                    }
                    taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                    date = inputLine.split("/by")[1].strip();
                    newTask = new DeadLineTask(taskName, date);
                    taskList.add(newTask);
                    ui.printTaskAdded(newTask, taskList.size());
                    break;

                case EVENT:
                    if (eventString.length < 2 | inputLine.split("/from").length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task name and event duration."
                                + "\nFormat: skynet.task taskName /from time /to time");
                    }

                    if (inputLine.split("/from")[1].split("/to").length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task name and event duration."
                                + "\nFormat: skynet.task taskName /from time /to time");
                    }

                    taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                    String[] duration = inputLine.split("/from")[1].split("/to");
                    String startDate = duration[0].strip();
                    String endDate = duration[1].strip();

                    newTask = new EventTask(taskName, startDate, endDate);
                    taskList.add(newTask);
                    ui.printTaskAdded(newTask, taskList.size());
                    break;

                case DELETE:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task index to delete.");
                    }
                    int indexToDelete = Integer.parseInt(eventString[1]);
                    ui.printDeletedTask(taskList.get(indexToDelete));
                    taskList.remove(indexToDelete);
                    break;

                case FIND:
                    if (eventString.length < 2) {
                        throw new MissingArgumentException("Please specify the skynet.task index to delete.");
                    }
                    String input = eventString[1];
                    TaskList results = taskList.findRelatedTasks(input);
                    System.out.println(results.toString());
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

    /**
     * Handles user input from the GUI.
     *
     * @param taskList  List of tasks.
     * @param ui        Interface for returning response.
     * @param inputLine Input given by the user.
     * @return Response String given by Skynet.
     */
    public static String handleGuiCommand(TaskList taskList, UI ui, String inputLine) {
        if (inputLine.equals("bye")) {
            return "EXIT";
        }

        String response;
        try {
            String[] eventString = inputLine.split("/")[0].split(" ");
            UserInput caseType = UserInput.fromString(eventString[0]);
            assert(caseType != null);

            String taskName;
            String date;

            Task newTask;
            switch (caseType) {
            case LIST:
                System.out.println(taskList.toString());
                response = taskList.toString();
                break;
            case MARK:
                if (eventString.length < 2) {
                    throw new MissingArgumentException("Please specify the skynet.task index to mark.");
                }
                int indexToMark = Integer.parseInt(eventString[1]);
                taskList.get(indexToMark).markTask();
                response = ui.printMark(taskList, indexToMark);
                break;

            case UNMARK:
                if (eventString.length < 2) {
                    throw new MissingArgumentException("Please specify the skynet.task index to unmark.");
                }
                int indexToUnmark = Integer.parseInt(eventString[1]);
                taskList.get(indexToUnmark).unMarkTask();
                response = ui.printUnMark(taskList, indexToUnmark);
                break;

            case TODO:
                if (eventString.length < 2) {
                    throw new MissingArgumentException("Please specify the skynet.task name.");
                }
                taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                newTask = new ToDoTask(taskName);
                taskList.add(newTask);
                response = ui.printTaskAdded(newTask, taskList.size());
                break;

            case DEADLINE:
                if (eventString.length < 2 | inputLine.split("/by").length < 2) {
                    throw new MissingArgumentException("Please specify the skynet.task name and deadline."
                            + "\nFormat: skynet.task taskName /by time");
                }
                taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                date = inputLine.split("/by")[1].strip();
                newTask = new DeadLineTask(taskName, date);
                taskList.add(newTask);
                response = ui.printTaskAdded(newTask, taskList.size());
                break;

            case EVENT:
                if (eventString.length < 2 | inputLine.split("/from").length < 2) {
                    throw new MissingArgumentException("Please specify the skynet.task name and event duration."
                            + "\nFormat: skynet.task taskName /from time /to time");
                }

                if (inputLine.split("/from")[1].split("/to").length < 2) {
                    throw new MissingArgumentException("Please specify the skynet.task name and event duration."
                            + "\nFormat: skynet.task taskName /from time /to time");
                }

                taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
                String[] duration = inputLine.split("/from")[1].split("/to");
                String startDate = duration[0].strip();
                String endDate = duration[1].strip();

                newTask = new EventTask(taskName, startDate, endDate);
                taskList.add(newTask);
                response = ui.printTaskAdded(newTask, taskList.size());
                break;

            case DELETE:
                if (eventString.length < 2) {
                    throw new MissingArgumentException("Please specify the skynet.task index to delete.");
                }
                int indexToDelete = Integer.parseInt(eventString[1]);
                response = ui.printDeletedTask(taskList.get(indexToDelete));
                taskList.remove(indexToDelete);
                break;

            case FIND:
                if (eventString.length < 2) {
                    throw new MissingArgumentException("Please specify the skynet.task index to delete.");
                }
                String input = eventString[1];
                TaskList results = taskList.findRelatedTasks(input);
                System.out.println(results.toString());
                response = results.toString();
                break;

            default:
                response = ui.printFailureToParseInput(inputLine);
            }
        } catch (MissingArgumentException e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) {
            return ui.showError("Command Error:\n" + e.getMessage());
        }
        assert(response != null);
        return response;
    }

}
