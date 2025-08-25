package sophia;
import sophia.DeadlineTaskParser;
import sophia.EventTaskParser;
import sophia.Parser;
import sophia.SophiaException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

import static java.lang.System.exit;

//used ChatGpt for quality check of code
public class Sophia {
    //Keep a list of internal tasks
    private static final String name = "Sophia";
    private final TaskList taskList;
    private final UI ui;
    private final Storage storage;
    public enum taskTypes{
        TODO("todo"),
        BYE("bye"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        DEADLINE("deadline"),
        EVENT("event"),
        LIST("list"),
        SAVE("save");
        public final String keyword;
        taskTypes(String keyword) {
            this.keyword = keyword;
        }
    }

    public Sophia(String filePath) {
        TaskList taskList1;
        ui = new UI();
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
        if(!Parser.validateListInput(input)) throw new SophiaException("Usage: list");
        ui.printLine();
        ui.printList(taskList);
    }

    private void addTask(String input,taskTypes Type) throws SophiaException {
        Task new_task;
        String[] segments =  input.split(" ");
        String description = String.join(" ",
                Arrays.copyOfRange(input.split(" "), 1, segments.length));

        switch (Type) {
            case TODO -> {
                if(!Parser.validateTodoInput(input)) throw new SophiaException("todo <description>");
                TodoTaskParser parser = new TodoTaskParser(description);
                new_task = parser.parse();
            }

            case DEADLINE -> {
                if(!Parser.validateDeadlineInput(input)) throw new SophiaException("deadline <description> /by <date>");
                DeadlineTaskParser parser = new DeadlineTaskParser(description);
                new_task = parser.parse();
            }

            case EVENT -> {
                if(!Parser.validateEventInput(input)) throw new SophiaException("event <description> /from <date> /to <date>");
                EventTaskParser parser = new EventTaskParser(description);
                new_task = parser.parse();
            }

            default -> {
                throw new SophiaException("Invalid Type of command");
            }
        }
        taskList.addTask(new_task);
        ui.printLine();
        ui.addTask(new_task,taskList);
    }

    public void handleTask(String userInputs, boolean done) throws SophiaException {

        if(!Parser.validateMarkInput(userInputs) && !Parser.validateUnmarkInput(userInputs) ){
            if(done) throw new SophiaException("Usage: mark <index>");
            else throw new SophiaException("Usage: unmark <index>");
        }

        int index = Integer.parseInt(userInputs.split(" ")[1])-1;

        if (index < 0 || index >= taskList.taskListSize()) {
            throw new SophiaException("No such task exists!");
        }

        taskList.setDone(index,done);
        ui.printLine();
        ui.markTask(taskList, done, index);
    }

    public void handleBye(String input) throws SophiaException {
        if(!Parser.validateByeInput(input)) throw new SophiaException("Usage: bye");
        ui.printLine();
        ui.exit();
        exit(-1);
    }

    public void saveTasks(String input) throws SophiaException {
        if(!Parser.validateSaveInput(input)) throw new SophiaException("Usage: save");
        try{
            storage.save(taskList);
        }catch(Exception e){
            throw new SophiaException(e.getMessage());
        }
        ui.printLine();
        ui.saved();
    }

    public void deleteTask(String input) throws SophiaException {
        if(!Parser.validateDeleteInput(input)){
            throw new SophiaException("Usage: delete <index>");
        }
        int index = Integer.parseInt(input.split(" ")[1])-1;
        if (index < 0 || index >= taskList.taskListSize()) {
            System.out.println("No such task exists!");
            return;
        }
        taskList.removeTask(index);
        ui.deleteTask(index,taskList);
    }

    private void run() throws SophiaException {
        ui.introduction(name);
        Scanner scanner = new Scanner(System.in);

        while(true) {
            if(!scanner.hasNextLine()) {
                System.out.println("Please enter a command");
            }
            String input = scanner.nextLine().trim();
            String[] userInputs = input.split("\\s+",2);
            String cmd = userInputs[0];

            taskTypes type = Arrays.stream(taskTypes.values())
                    .filter(t -> t.keyword.equals(cmd))
                    .findFirst()
                    .orElse(null);

            if(type == null) {
                throw new SophiaException("Invalid command: " + cmd);
            }
            try {
                switch (type) {
                    case BYE -> handleBye(input);
                    case LIST -> printList(input);
                    case MARK -> handleTask(input, true);
                    case UNMARK -> handleTask(input, false);
                    case TODO -> addTask(input, taskTypes.TODO);
                    case EVENT -> addTask(input, taskTypes.EVENT);
                    case DEADLINE -> addTask(input, taskTypes.DEADLINE);
                    case DELETE -> deleteTask(input);
                    case SAVE -> saveTasks(input);
                    default -> System.out.println("Invalid Command: " + cmd);
                }
            } catch (SophiaException e) {
                ui.showError(e);
            }
        }
        //scanner.close();
    }

    public static void main(String[] args) {
        try {
            new Sophia("../data/test.txt").run();
        } catch (SophiaException e) {
            System.out.println(e.getMessage());
        }
    }
}
