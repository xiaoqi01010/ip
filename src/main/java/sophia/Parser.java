package sophia;

import java.util.regex.Pattern;
/**
 * Represents a parser interface for interpreting user input into {@link Task} objects.
 * <p>
 * Implementations of {@code Parser} are responsible for parsing specific command strings
 * and converting them into the appropriate {@code Task} or triggering the relevant actions.
 * </p>
 * <p>
 * This interface also provides static utility methods to validate user input
 * against expected command formats using regular expressions.
 * </p>
 */
public interface Parser {
    /**
     * Parses the user input and returns a corresponding {@link Task}.
     *
     * @return a {@code Task} object created from the parsed input
     * @throws SophiaException if the input cannot be parsed or is invalid
     */
    public Task parse() throws SophiaException;


    /**
     * Validates the given string against a specified regular expression pattern.
     *
     * @param str     the input string to validate
     * @param pattern the regex pattern to match against
     * @return {@code true} if the string matches the pattern, {@code false} otherwise
     */
    public static boolean validateInput(String str, Pattern pattern) {
        return pattern.matcher(str).matches();
    }

    /**
     * Validates whether the input string is a valid {@code mark} command.
     * <p>Expected format: {@code mark <index>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateMarkInput(String str) {
        return validateInput(str, Pattern.compile("^mark\\s+\\d+$"));
    }

    /**
     * Validates whether the input string is a valid {@code unmark} command.
     * <p>Expected format: {@code unmark <index>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateUnmarkInput(String str) {
        return validateInput(str, Pattern.compile("^unmark\\s+\\d+$"));
    }

    /**
     * Validates whether the input string is a valid {@code delete} command.
     * <p>Expected format: {@code delete <index>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateDeleteInput(String str) {
        return validateInput(str, Pattern.compile("^delete\\s+\\d+$"));
    }

    /**
     * Validates whether the input string is a valid {@code todo} command.
     * <p>Expected format: {@code todo <description>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateTodoInput(String str) {
        return validateInput(str, Pattern.compile("^todo\\s+.+$"));
    }

    /**
     * Validates whether the input string is a valid {@code deadline} command.
     * <p>Expected format: {@code deadline <description> /by <date>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateDeadlineInput(String str) {
        return validateInput(str, Pattern.compile("^deadline\\s+.+\\s+/by\\s+.+$"));
    }

    /**
     * Validates whether the input string is a valid {@code event} command.
     * <p>Expected format: {@code event <description> /from <start> /to <end>}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateEventInput(String str) {
        return validateInput(str, Pattern.compile("^event\\s+.+\\s+/from\\s+.+\\s+/to\\s+.+$"));
    }

    /**
     * Validates whether the input string is a valid {@code list} command.
     * <p>Expected format: {@code list}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateListInput(String str) {
        return validateInput(str, Pattern.compile("^list$"));
    }

    /**
     * Validates whether the input string is a valid {@code bye} command.
     * <p>Expected format: {@code bye}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateByeInput(String str) {
        return validateInput(str, Pattern.compile("^bye$"));
    }

    /**
     * Validates whether the input string is a valid {@code save} command.
     * <p>Expected format: {@code save}</p>
     *
     * @param str the input string
     * @return {@code true} if valid, {@code false} otherwise
     */
    public static boolean validateSaveInput(String str) {
        return validateInput(str, Pattern.compile("^save$"));
    }

}
