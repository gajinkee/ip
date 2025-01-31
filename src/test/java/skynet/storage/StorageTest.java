package skynet.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import skynet.task.DeadLineTask;
import skynet.task.EventTask;
import skynet.task.Task;
import skynet.task.ToDoTask;


public class StorageTest {

    private static final String TEST_FILE = "testFile";

    @Test
    public void load_possibleUserInputs_handledCorrectly() {

        // Arrange - Create test file
        String fileContent = """
                [T][ ] Read book
                [D][X] Submit assignment (by: 2024-02-01)
                [E][ ] Project meeting (from: 10AM to: 12PM)
                """;
        try {
            final Path path = Path.of(TEST_FILE);
            Files.write(path, fileContent.getBytes());

            // Act - Load tasks from file
            ArrayList<Task> tasks = Storage.load(TEST_FILE);

            // Assert - Verify task details
            assertEquals(3, tasks.size());

            // Verify ToDo task
            assertInstanceOf(ToDoTask.class, tasks.get(0));
            assertEquals("[T][ ] Read book", tasks.get(0).toString());

            // Verify Deadline task
            assertInstanceOf(DeadLineTask.class, tasks.get(1));
            assertEquals("[D][X] Submit assignment (by: 2024-02-01)", tasks.get(1).toString());

            // Verify Event task
            assertInstanceOf(EventTask.class, tasks.get(2));
            assertEquals("[E][ ] Project meeting (from: 10AM to: 12PM)", tasks.get(2).toString());

            // Cleanup
            Files.delete(path);
        } catch (Exception e) {
            System.out.println("Storage test has issues: " + e);
        }

    }

    @Test
    public void save_possibleUserInputs_savedCorrectly() {

        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new ToDoTask("Read book"));
        tasks.add(new DeadLineTask("Submit assignment", "2024-02-01"));
        tasks.add(new EventTask("Project meeting", "10AM", "12PM"));

        // Act - Save tasks to file
        try {
            Storage.save(TEST_FILE, tasks);

            final Path path = Path.of(TEST_FILE);
            List<String> lines = Files.readAllLines(path);

            assertEquals(3, lines.size());
            assertEquals("[T][ ] Read book", lines.get(0));
            assertEquals("[D][ ] Submit assignment (by: 2024-02-01)", lines.get(1));
            assertEquals("[E][ ] Project meeting (from: 10AM to: 12PM)", lines.get(2));

            Files.delete(path);
        } catch (Exception e) {
            System.out.println("saving errors out: " + e);
        }

    }
}
