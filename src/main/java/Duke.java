import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.nio.file.*;

import static java.lang.System.exit;

//used ChatGpt for quality check of code
public class Duke {
    //Keep a list of internal tasks
    private static final String name = "Duke";
    private final List<Task> tasks = new ArrayList<>();
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

    private void printList(String input) throws DukeException{
        if(!validateListInput(input)) throw new DukeException("Usage: list");
        System.out.println("__________________________________________________");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    private void addTask(String input,taskTypes Type) throws DukeException {
        Task new_task;
        String[] segments =  input.split(" ");
        String description = String.join(" ",
                Arrays.copyOfRange(input.split(" "), 1, segments.length));

        switch (Type) {

            case TODO -> {
                if(!validateTodoInput(input)) throw new DukeException("todo <description>");
                TodoTaskParser parser = new TodoTaskParser(description);
                new_task = parser.parse();
            }

            case DEADLINE -> {
                if(!validateDeadlineInput(input)) throw new DukeException("deadline <description> /by <date>");
                DeadlineTaskParser parser = new DeadlineTaskParser(description);
                new_task = parser.parse();
            }

            case EVENT -> {
                if(!validateEventInput(input)) throw new DukeException("event <description> /from <date> /to <date>");
                EventTaskParser parser = new EventTaskParser(description);
                new_task = parser.parse();
            }

            default -> {
                throw new DukeException("Invalid Type of command");
            }
        }

        //System.out.print(ch);
        tasks.add(new_task);
        System.out.println("__________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(new_task);
        System.out.println("Now you have "+tasks.size()+" tasks in the list");
    }

    public void handleTask(String userInputs, boolean done) throws DukeException {

        if(!validateMarkInput(userInputs) && !validateUnmarkInput(userInputs) ){
            if(done) throw new DukeException("Usage: mark <index>");
            else throw new DukeException("Usage: unmark <index>");
        }

        int index;

        try{
            index = Integer.parseInt(userInputs.split(" ")[1])-1;
        } catch(NumberFormatException e){
            System.out.println(e.getMessage()+" please enter a valid number because the string cannot be converted to a valid integer. ");
            return;
        }

        if (index < 0 || index >= tasks.size()) {
            System.out.println("No such task exists!");
            return;
        }

        tasks.get(index).setDone(done);
        System.out.println("__________________________________________________");
        System.out.println((done ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n")
                + tasks.get(index).toString()
        );
    }

    public void handleBye(Scanner scanner,String input) throws DukeException {
        if(!validateByeInput(input)) throw new DukeException("Usage: bye");
        scanner.close();
        System.out.println("__________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        exit(-1);
    }

    public void saveTasks(String input) throws DukeException{
        try{
            File directory = new File("./data/");
            if(!directory.exists()) {
                System.out.println("./data/: No such directory exists!");
                directory.mkdir();
            }
            File task_file = new File("./data/duke.txt");
            if(!task_file.exists()){
                task_file.createNewFile();
            }
            FileWriter fw = new FileWriter(task_file);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Task task: tasks){
                task.write(bw);
            }
            bw.close();
        }catch(Exception e){
            throw new DukeException(e.getMessage());
        }

        System.out.println("___________________________________________________");
        System.out.println("Saved tasks to file");
    }
    public void deleteTask(String input) throws DukeException{
        if(!validateDeleteInput(input)){
            throw new DukeException("Usage: delete <index>");
        }

        int index;
        try{
            index = Integer.parseInt(input.split(" ")[1])-1;
            //System.out.println(index);
        } catch(NumberFormatException e){
            System.out.println(e.getMessage() + ", the string cannot be converted into a valid integer. Please enter a valid index.");
            return;
        }

        if (index < 0 || index >= tasks.size()) {
            System.out.println("No such task exists!");
            return;
        }

        System.out.println("Noted. I've removed this task:\n"
                + tasks.get(index).toString()
        );

        tasks.remove(index);
        System.out.println("__________________________________________________");
        System.out.println("Now you have "+tasks.size()+" tasks in the list");
    }

    private static boolean validateInput(String str, Pattern pattern) {
        return pattern.matcher(str).matches();
    }

    private static boolean validateMarkInput(String str) {
        return validateInput(str, Pattern.compile("^mark\\s+.+$"));
    }

    private static boolean validateUnmarkInput(String str) {
        return validateInput(str, Pattern.compile("^unmark\\s+.+$"));
    }

    private static boolean validateDeleteInput(String str) {
        return validateInput(str, Pattern.compile("^delete\\s+.+$"));
    }

    private static boolean validateTodoInput(String str) {
        return validateInput(str, Pattern.compile("^todo\\s+.+$"));
    }

    private static boolean validateDeadlineInput(String str) {
        return validateInput(str, Pattern.compile("^deadline\\s+.+\\s+/by\\s+.+$"));
    }

    private static boolean validateEventInput(String str) {
        return validateInput(str, Pattern.compile("^event\\s+.+\\s+/from\\s+.+\\s+/to\\s+.+$"));
    }

    private static boolean validateListInput(String str) {
        return validateInput(str, Pattern.compile("^list$"));
    }

    private static boolean validateByeInput(String str) {
        return validateInput(str, Pattern.compile("^bye$"));
    }
    private void run() throws DukeException {
        System.out.println("__________________________________________________");
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            String[] userInputs = input.split("\\s+",2);
            String cmd = userInputs[0];

            taskTypes type = Arrays.stream(taskTypes.values())
                    .filter(t -> t.keyword.equals(cmd))
                    .findFirst()
                    .orElse(null);

            if(type == null) {
                throw new DukeException("Invalid command: " + cmd);
            }
            switch(type) {
                case BYE -> handleBye(scanner,input);
                case LIST -> printList(input);
                case MARK -> handleTask(input,true);
                case UNMARK-> handleTask(input,false);
                case TODO -> addTask(input,taskTypes.TODO);
                case EVENT-> addTask(input,taskTypes.EVENT);
                case DEADLINE-> addTask(input,taskTypes.DEADLINE);
                case DELETE -> deleteTask(input);
                case SAVE -> saveTasks(input);
                default -> System.out.println("Invalid Command: " + cmd);
            }
        }
    }
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
