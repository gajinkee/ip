package skynet.ui;

import skynet.exceptions.MissingArgumentException;
import skynet.task.TaskList;

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
        try {
            while (true) {
                String inputLine = ui.scanNextLine();
                assert(inputLine != null);
                if (inputLine.equals("bye")) {
                    break;
                }
                commandParse(taskList, ui, inputLine);
            }
            ui.printGoodBye();
            ui.closeScanner();
        } catch (MissingArgumentException e) {
            ui.showError(e.getMessage());
        } catch (Exception e) {
            ui.showError("Command Error:\n" + e.getMessage());
        }
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
        try {
            if (inputLine.equals("bye")) {
                return "EXIT";
            }
            return commandParse(taskList, ui, inputLine);
        } catch (MissingArgumentException e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) {
            return ui.showError("Command Error:\n" + e.getMessage());
        }
    }

    /**
     * Parses command cases.
     *
     * @param taskList  List of current tasks.
     * @param ui        UI to display and read from.
     * @param inputLine input given by user.
     * @return String of the completed command.
     * @throws MissingArgumentException throw argument errors if wrong format input.
     */
    private static String commandParse(TaskList taskList, UI ui, String inputLine) throws MissingArgumentException {
        String[] eventString = inputLine.split("/")[0].split(" ");
        Command caseType = Command.fromString(eventString[0]);
        assert(caseType != null);
        return switch (caseType) {
            case LIST -> Command.listCommand(taskList, ui);
            case MARK -> Command.markCommand(taskList, ui, eventString);
            case UNMARK -> Command.unmarkCommand(taskList, ui, eventString);
            case TODO -> Command.todoCommand(taskList, ui, eventString);
            case DEADLINE -> Command.deadlineCommand(taskList, ui, eventString, inputLine);
            case EVENT -> Command.eventCommand(taskList, ui, eventString, inputLine);
            case DELETE -> Command.deleteCommand(taskList, ui, eventString);
            case FIND -> Command.findCommand(taskList, eventString);
        };
    }
}
