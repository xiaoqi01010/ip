package sophia;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Represents a task with a deadline.
 * <p>
 * A {@code DeadlineTask} extends {@link Task} and stores a description
 * and a deadline date.
 * </p>
 */
public final class DeadlineTask extends Task {
    private String ddl;
    /**
     * Constructs a new {@code DeadlineTask}.
     *
     * @param input the task description
     * @param ddl   the deadline date (expected format: yyyy-MM-dd or free text)
     */
    public DeadlineTask(String input, String ddl) {
        super(input);
        this.ddl = ddl;
    }

    /**
     * Writes this deadline task to the given {@link BufferedWriter}.
     * <p>
     * Format: {@code D | <done> | <name> | <deadline>}
     * </p>
     *
     * @param bw the writer to output task data
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void write(BufferedWriter bw) throws IOException {
        bw.write("D | " + (isDone()? 1:0) + " | "
                + getName() + " | " + parseDate(ddl) + "\n");
        bw.flush();
    }

    /**
     * Returns a human-readable string representation of the deadline task.
     *
     * @return formatted string with description and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseDate(ddl) + ")";
    }
}
