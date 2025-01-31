package skynet.ui;

import java.util.Arrays;

/**
 * Enumerator for possible user input commands
 */
public enum UserInput {
    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND;

    /**
     * Converts userinput from string to the Enum type.
     * @param input User input to convert.
     * @return UserInput Possible enums values.
     */
    public static UserInput fromString(String input) {
        return Arrays.stream(values())
                .filter(command -> command.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sorry I dont understand: " + input));
    }

}
