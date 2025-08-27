package sophia;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.exit;


/**
 * Main chatbot class
 */
//used ChatGpt for quality check of code
public class Sophia {
    //Keep a list of internal tasks
    private static final UI ui = new UI();
    private static final String NAME = "Sophia";
    private final TaskList taskList;
    private final Storage storage;
    /**
     * Returns a Sophia Object which performs the chatting
     * <p>
     * @param filePath specifies a valid file path.
     */
    public Sophia(String filePath) {
        TaskList taskList1;
        storage = new Storage(filePath);
        try {
            taskList1 = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showError(e);
            taskList1 = new TaskList();
        }
        this.taskList = taskList1;
    }

    private void printList(String input) throws SophiaException {
        if (!Parser.validateListInput(input)) {
            throw new SophiaException("Usage: list");
        }
        ui.printLine();
        ui.printList(taskList);
    }

    //public for testing
    private Task addTodo(String input, String description) throws SophiaException {
        if (!Parser.validateTodoInput(input)) {
            throw new SophiaException("todo <description>");
        }
        TodoTaskParser parser = new TodoTaskParser(description);
        return parser.parse();
    }

    //public for testing
    private Task addDeadline(String input, String description) throws SophiaException {
        if (!Parser.validateDeadlineInput(input)) {
            throw new SophiaException("deadline <description> /by <date>");
        }
        DeadlineTaskParser parser = new DeadlineTaskParser(description);
        return parser.parse();
    }

    //public for testing
    private Task addEvent(String input, String description) throws SophiaException {
        if (!Parser.validateEventInput(input)) {
            throw new SophiaException("event <description> /from <date> /to <date>");
        }
        EventTaskParser parser = new EventTaskParser(description);
        return parser.parse();
    }

    private void addTask(String input, TaskType Type) throws SophiaException {
        Task new_task;
        String[] segments = input.split(" ");
        String description = String.join(" ",
                Arrays.copyOfRange(input.split(" "), 1, segments.length));

        switch (Type) {
        case TODO -> new_task = addTodo(input, description);
        case DEADLINE -> new_task = addDeadline(input, description);
        case EVENT -> new_task = addEvent(input, description);
        default -> throw new SophiaException("Invalid Type of command");
        }

        taskList.addTask(new_task);
        ui.printLine();
        ui.addTask(new_task, taskList);
    }

    private void handleTask(String userInputs, boolean done) throws SophiaException {
        if (!Parser.validateMarkInput(userInputs)
                && !Parser.validateUnmarkInput(userInputs) ) {
            if (done) {
                throw new SophiaException("Usage: mark <index>");
            }
            else {
                throw new SophiaException("Usage: unmark <index>");
            }
        }

        int index = Integer.parseInt(userInputs.split(" ")[1]) - 1;
        if (index < 0 || index >= taskList.taskListSize()) {
            throw new SophiaException("No such task exists!");
        }

        taskList.setDone(index, done);
        ui.printLine();
        ui.markTask(taskList, done, index);
    }

    private void handleBye(String input) throws SophiaException {
        if (!Parser.validateByeInput(input)) {
            throw new SophiaException("Usage: bye");
        }
        ui.printLine();
        ui.exit();
        exit(0);
    }

    private void saveTasks(String input) throws SophiaException {
        if (!Parser.validateSaveInput(input)) {
            throw new SophiaException("Usage: save");
        }

        try {
            storage.save(taskList);
        } catch (Exception e) {
            throw new SophiaException(e.getMessage());
        }

        ui.printLine();
        ui.saved();
    }

    public String showTasks() {
        return taskList.printList();
    }

    private void findTask(String input) throws SophiaException {
        if (!Parser.validateFindInput(input)) {
            throw new SophiaException("Usage: find <keyword>");
        }
        String keyword = input.split(" ")[1].trim();
        List<Task> xs = taskList.findTask(keyword);
        ui.printLine();
        ui.printTasksFound(xs);
    }

    /**
     * deletTask is responsible for deleting a task from taskList
     * @param input
     * @throws SophiaException
     */
    public void deleteTask(String input) throws SophiaException {
        if (!Parser.validateDeleteInput(input)) {
            throw new SophiaException("Usage: delete <index>");
        }

        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0 || index >= taskList.taskListSize()) {
            throw new SophiaException("No such task exists!");
        }
        ui.deleteTask(index, taskList);
        taskList.removeTask(index);
    }

    private void run(String input) throws SophiaException {
        String[] userInputs = input.split("\\s+", 2);
        String cmd = userInputs[0];

        TaskType type = Arrays.stream(TaskType.values())
                .filter(t -> t.keyword.equals(cmd))
                .findFirst()
                .orElse(null);

        if (type == null) {
            throw new SophiaException("Invalid command: " + cmd);
        }

        try {
            switch (type) {
            case BYE -> handleBye(input);
            case LIST -> printList(input);
            case MARK -> handleTask(input, true);
            case UNMARK -> handleTask(input, false);
            case TODO -> addTask(input, TaskType.TODO);
            case EVENT -> addTask(input, TaskType.EVENT);
            case DEADLINE -> addTask(input, TaskType.DEADLINE);
            case DELETE -> deleteTask(input);
            case SAVE -> saveTasks(input);
            case FIND -> findTask(input);
            default -> throw new SophiaException("Invalid Command: " + cmd);
            }
        } catch (SophiaException e) {
            ui.showError(e);
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Sophia sophia = new Sophia("./data/test.txt");
            ui.introduction(NAME);
            while (true) {
                if (!scanner.hasNextLine()) {
                    ui.printEmptyMessage();
                }
                String input = scanner.nextLine().trim();
                sophia.run(input);
            }

        } catch (SophiaException e) {
            System.out.println(e.getMessage());
        }
    }
}
