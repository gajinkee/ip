package storage;

import task.DeadLineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;

public class StorageHandler {

    public static ArrayList<Task> load(String fileName) throws IOException{

        ArrayList<Task> taskArray = new ArrayList<>();
        final Path path = Path.of(fileName);
        if (!Files.exists(path)) {
            return taskArray;
        }

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            ArrayList<String> parseLine = parseDataLine(line);
            Task task = getTask(parseLine);
            taskArray.add(task);
        }
        return taskArray;
    }

    private static ArrayList<String> parseDataLine(String line) {

        ArrayList<String> parsedLine = new ArrayList<>();
        String type = line.substring(0, 3);
        String isDone = line.substring(3, 6);
        String taskName = line.substring(7).split("\\(")[0];

        parsedLine.add(type);
        parsedLine.add(isDone);
        parsedLine.add(taskName);

        if (type.equals("[E]") | type.equals("[D]")) {
            String details = line.split("\\(")[1];
            parsedLine.add(details.substring(0,details.length()-1));
        }

        return parsedLine;
    }

    private static Task getTask(ArrayList<String> parseLine) {

        String taskType = parseLine.get(0);

        String isDone = parseLine.get(1);
        String taskName = parseLine.get(2).strip();
        String taskDetails;
        Task task;

        switch (taskType) {
        case "[D]":
            taskDetails = parseLine.get(3).strip();
            String deadline = taskDetails.split("by: ")[1].strip();
            task = new DeadLineTask(taskName,deadline);

            break;
        case "[E]":
            taskDetails = parseLine.get(3).strip();
            String to = taskDetails.split("to: ")[1].strip();
            String from = taskDetails.split("to: ")[0].split("from: ")[1].strip();
            task = new EventTask(taskName,from,to);

            break;
        default:
            task = new ToDoTask(taskName);
            break;
        }
        if (isDone.equals("[X]")) {
            task.markTask();
        }
        return task;
    }

    public static void save(String fileName, ArrayList<Task> taskArray) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        for (Task task : taskArray) {
            lines.add(task.toString());
        }
        Files.write(Path.of(fileName), lines);
    }
}
