package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class DeadLineTask extends Task {
    private final String deadlineStringFormat;
    private final LocalDateTime deadlineDateTimeFormat;

    public DeadLineTask(String name, String deadline) {
        super(name);
        this.deadlineStringFormat = deadline;

        LocalDateTime parsedDateTime;
        try {
            // 2/12/2019 1800 as 2nd of December 2019, 6pm,
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            parsedDateTime = LocalDateTime.parse(deadline,formatter);
        } catch (DateTimeParseException e) {
            parsedDateTime = null;
        }

        this.deadlineDateTimeFormat = parsedDateTime;
    }

    private String getFormattedDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
        return this.deadlineDateTimeFormat.format(formatter);
    }

    @Override
    public String toString() {
        String datetime = this.deadlineDateTimeFormat == null ?
                this.deadlineStringFormat :
                this.getFormattedDateTime();
        return "[D]" + super.toString() + String.format(" (by: %s)", datetime);
    }
}
