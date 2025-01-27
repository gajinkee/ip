package skynet;

import skynet.storage.Storage;
import skynet.task.TaskList;
import skynet.ui.UI;

public class Skynet {
    public static void main(String[] args) {
        try {

            UI ui = new UI();
            ui.showWelcome();

            TaskList taskList = new TaskList(Storage.load("data.txt"));
            Parser.handleCommand(taskList,ui);
            Storage.save("data.txt", taskList.getTasks());

        } catch (Exception e) {
            System.out.println("Error in main:\n" + e.getMessage());
        }

    }
}
