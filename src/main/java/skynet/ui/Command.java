package skynet.ui;

import java.util.Arrays;

import skynet.exceptions.MissingArgumentException;
import skynet.task.DeadLineTask;
import skynet.task.EventTask;
import skynet.task.Task;
import skynet.task.TaskList;
import skynet.task.ToDoTask;

/**
 * Enumerator for possible user input commands
 */
public enum Command {
    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND;

    /**
     * Converts userinput from string to the Enum type.
     *
     * @param input User input to convert.
     * @return UserInput Possible enums values.
     */
    public static Command fromString(String input) {
        return Arrays.stream(values())
                .filter(command -> command.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sorry I dont understand: " + input));
    }

    /**
     * Handler for the Find Command.
     * @param taskList list of task.
     * @param eventString string of events.
     * @return results of the find.
     * @throws MissingArgumentException invalid arguments.
     */
    public static String findCommand(TaskList taskList, String[] eventString) throws MissingArgumentException {
        if (eventString.length < 2) {
            throw new MissingArgumentException("Please specify the skynet.task index to delete.");
        }
        String input = eventString[1];
        TaskList results = taskList.findRelatedTasks(input);
        System.out.println(results.toString());
        return results.toString();
    }

    /**
     * Handler for the Delete Command.
     * @param taskList list of task.
     * @param ui UI used for input output.
     * @param eventString string of events.
     * @return results of the delete command.
     * @throws MissingArgumentException invalid arguments.
     */
    public static String deleteCommand(TaskList taskList, UI ui, String[] eventString) throws MissingArgumentException {
        if (eventString.length < 2) {
            throw new MissingArgumentException("Please specify the skynet.task index to delete.");
        }
        int indexToDelete = Integer.parseInt(eventString[1]);
        ui.printDeletedTask(taskList.get(indexToDelete));
        String response = ui.printDeletedTask(taskList.get(indexToDelete));
        taskList.remove(indexToDelete);
        return response;
    }

    /**
     * Handler for the Event Command.
     * @param taskList list of task.
     * @param ui UI used for input output.
     * @param eventString string of events.
     * @param inputLine input by user.
     * @return results of the event command.
     * @throws MissingArgumentException invalid arguments.
     */
    public static String eventCommand(TaskList taskList, UI ui, String[] eventString, String inputLine)
            throws MissingArgumentException {
        if (eventString.length < 2 | inputLine.split("/from").length < 2) {
            throw new MissingArgumentException("Please specify the skynet.task name and event duration."
                    + "\nFormat: skynet.task taskName /from time /to time");
        }
        if (inputLine.split("/from")[1].split("/to").length < 2) {
            throw new MissingArgumentException("Please specify the skynet.task name and event duration."
                    + "\nFormat: skynet.task taskName /from time /to time");
        }

        final Task newTask = eventTaskBuilder(eventString, inputLine);
        taskList.add(newTask);
        return ui.printTaskAdded(newTask, taskList.size());
    }

    /**
     * Private method for creating a EventTask.
     * @param eventString string of events.
     * @param inputLine input by user.
     * @return Returns new eventTask.
     */
    private static Task eventTaskBuilder(String[] eventString, String inputLine) {
        String taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
        String[] duration = inputLine.split("/from")[1].split("/to");
        String startDate = duration[0].strip();
        String endDate = duration[1].strip();
        return new EventTask(taskName, startDate, endDate);
    }

    /**
     * Handler for the Deadline Command.
     * @param taskList list of task.
     * @param ui UI used for input output.
     * @param eventString string of events.
     * @param inputLine input by user.
     * @return results of the deadline command.
     * @throws MissingArgumentException invalid arguments.
     */
    public static String deadlineCommand(TaskList taskList, UI ui, String[] eventString, String inputLine)
            throws MissingArgumentException {
        if (eventString.length < 2 | inputLine.split("/by").length < 2) {
            throw new MissingArgumentException("Please specify the skynet.task name and deadline."
                    + "\nFormat: skynet.task taskName /by time");
        }
        String taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
        String date = inputLine.split("/by")[1].strip();
        Task newTask = new DeadLineTask(taskName, date);
        taskList.add(newTask);
        return ui.printTaskAdded(newTask, taskList.size());
    }

    /**
     * Handler for the to do Command.
     * @param taskList list of task.
     * @param ui UI used for input output.
     * @param eventString string of events.
     * @return results of the to do command.
     * @throws MissingArgumentException invalid arguments.
     */
    public static String todoCommand(TaskList taskList, UI ui, String[] eventString) throws MissingArgumentException {
        if (eventString.length < 2) {
            throw new MissingArgumentException("Please specify the skynet.task name.");
        }
        String taskName = String.join(" ", Arrays.asList(eventString).subList(1, eventString.length));
        Task newTask = new ToDoTask(taskName);
        taskList.add(newTask);
        return ui.printTaskAdded(newTask, taskList.size());
    }

    /**
     * Handler for the unmark Command.
     * @param taskList list of task.
     * @param ui UI used for input output.
     * @param eventString string of events.
     * @return results of the unmark command.
     * @throws MissingArgumentException invalid arguments.
     */
    public static String unmarkCommand(TaskList taskList, UI ui, String[] eventString) throws MissingArgumentException {
        if (eventString.length < 2) {
            throw new MissingArgumentException("Please specify the skynet.task index to unmark.");
        }
        int indexToUnmark = Integer.parseInt(eventString[1]);
        taskList.get(indexToUnmark).unMarkTask();
        return ui.printUnMark(taskList, indexToUnmark);
    }

    /**
     * Handler for the list Command.
     * @param taskList list of task.
     * @param ui UI used for input output.
     * @return results of the list command.
     */
    public static String listCommand(TaskList taskList, UI ui) {
        return ui.printList(taskList.toString());
    }

    /**
     * Handler for the mark Command.
     * @param taskList list of task.
     * @param ui UI used for input output.
     * @param eventString string of events.
     * @return results of the mark command.
     * @throws MissingArgumentException invalid arguments.
     */
    public static String markCommand(TaskList taskList, UI ui, String[] eventString) throws MissingArgumentException {
        if (eventString.length < 2) {
            throw new MissingArgumentException("Please specify the skynet.task index to mark.");
        }
        int indexToMark = Integer.parseInt(eventString[1]);
        taskList.get(indexToMark).markTask();
        return ui.printMark(taskList, indexToMark);
    }


}
