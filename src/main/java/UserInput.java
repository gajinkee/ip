import java.util.Arrays;

public enum UserInput {
    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT,DELETE;

    public static UserInput fromString(String input) {
        return Arrays.stream(values())
                .filter(command -> command.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sorry I dont understand: " + input));
    }

}
