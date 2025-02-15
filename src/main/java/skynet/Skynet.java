package skynet;

import skynet.storage.Storage;
import skynet.task.TaskList;
import skynet.ui.Parser;
import skynet.ui.UI;

/**
 * Main function for Skynet application
 */
public class Skynet {
    public static void main(String[] args) {
        try {

            UI ui = new UI();
            ui.showWelcome();

            TaskList taskList = new TaskList(Storage.load("data.txt"));
            Parser.handleCommand(taskList, ui);
            Storage.save("data.txt", taskList.getTasks());

        } catch (Exception e) {
            System.out.println("Error in main:\n" + e.getMessage());
        }

    }

    /**
     * Returns response for GUI interface.
     *
     * @param input User Input.
     * @return response.
     */
    public String getResponse(String input) {

        String res;
        try {

            UI ui = new UI();

            TaskList taskList = new TaskList(Storage.load("data.txt"));
            res = Parser.handleGuiCommand(taskList, ui, input);
            Storage.save("data.txt", taskList.getTasks());

        } catch (Exception e) {
            System.out.println("Error in main:\n" + e.getMessage());
            return "Error in main:\n" + e.getMessage();
        }
        return res;
    }
}
