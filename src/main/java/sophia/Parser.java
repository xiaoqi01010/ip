package sophia;

import java.util.regex.Pattern;

public interface Parser {
    public Task parse() throws SophiaException;

    public static boolean validateInput(String str, Pattern pattern) {
        return pattern.matcher(str).matches();
    }

    public static boolean validateMarkInput(String str) {
        return validateInput(str, Pattern.compile("^mark\\s+\\d+$"));
    }

    public static boolean validateUnmarkInput(String str) {
        return validateInput(str, Pattern.compile("^unmark\\s+\\d+$"));
    }

    public static boolean validateDeleteInput(String str) {
        return validateInput(str, Pattern.compile("^delete\\s+\\d+$"));
    }

    public static boolean validateTodoInput(String str) {
        return validateInput(str, Pattern.compile("^todo\\s+.+$"));
    }

    public static boolean validateDeadlineInput(String str) {
        return validateInput(str, Pattern.compile("^deadline\\s+.+\\s+/by\\s+.+$"));
    }

    public static boolean validateEventInput(String str) {
        return validateInput(str, Pattern.compile("^event\\s+.+\\s+/from\\s+.+\\s+/to\\s+.+$"));
    }

    public static boolean validateListInput(String str) {
        return validateInput(str, Pattern.compile("^list$"));
    }

    public static boolean validateByeInput(String str) {
        return validateInput(str, Pattern.compile("^bye$"));
    }

    public static boolean validateSaveInput(String str) {
        return validateInput(str, Pattern.compile("^save$"));
    }

}
