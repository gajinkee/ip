package skynet.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import skynet.task.DeadLineTask;
import skynet.task.Task;
import skynet.task.TaskList;
import skynet.task.ToDoTask;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {

    private TaskList taskArray;

    private void simulateUserInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Simulate user input
    }

    @BeforeEach
    public void setUp() {
        taskArray = new TaskList();
        System.setOut(new PrintStream(new ByteArrayOutputStream())); // Capture output
    }

    @Test
    public void handleCommand_addToDoTask_taskAddedSuccessfully() {
        simulateUserInput("todo Read book\nbye\n");

        UI ui = new UI();
        Parser.handleCommand(taskArray, ui);

        assertEquals(1, taskArray.size());
        Task task = taskArray.get(0);
        assertInstanceOf(ToDoTask.class, task);
        assertEquals("[T][ ] Read book", task.toString());
    }

    @Test
    public void handleCommand_addDeadlineTask_taskAddedSuccessfully() {
        simulateUserInput("deadline Submit assignment /by 2024-02-01\nbye\n");

        UI ui = new UI();
        Parser.handleCommand(taskArray, ui);

        assertEquals(1, taskArray.size());
        Task task = taskArray.get(0);
        assertInstanceOf(DeadLineTask.class, task);
        assertEquals("[D][ ] Submit assignment (by: 2024-02-01)", task.toString());
    }

    @Test
    public void handleCommand_markTask_taskMarkedSuccessfully() {
        taskArray.add(new ToDoTask("Finish homework"));
        simulateUserInput("mark 0\nbye\n");

        UI ui = new UI();
        Parser.handleCommand(taskArray, ui);

        assertEquals("[T][X] Finish homework", taskArray.get(0).toString());
    }
}
