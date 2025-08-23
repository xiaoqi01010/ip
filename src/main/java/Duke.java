import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static java.lang.System.exit;

//used ChatGpt for quality check of code
public class Duke {
    //Keep a list of internal tasks
    private final List<Task> tasks = new ArrayList<>();
    private final String name;
    public enum taskTypes{TODO, DEADLINE,EVENT}
    public Duke(String name) {
        this.name = name;
    }
    private void printList(){
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).toString());
        }
    }
    private void addTask(String[] input,taskTypes Type) throws DukeException{
        Task new_task;
        //System.out.print(Type);
        if(input.length <= 1) {throw new DukeException("Invalid command too few arguments");}
        switch (Type) {
            case TODO -> {
                TodoTaskParser parser = new TodoTaskParser(input[1]);
                new_task = parser.parse();
            }
            case DEADLINE -> {
                DeadlineTaskParser parser = new DeadlineTaskParser(input[1]);
                new_task = parser.parse();
            }
            case EVENT -> {
                EventTaskParser parser = new EventTaskParser(input[1]);
                new_task = parser.parse();
            }
            default -> {
                throw new DukeException("Invalid Type of command");
            }
        }
        //System.out.print(ch);
        tasks.add(new_task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(new_task);
        System.out.println("Now you have "+tasks.size()+" tasks in the list");
    }
    public void handleTask(String[] userInputs){
        if(userInputs.length != 2) {
            System.out.println("Usage: "+userInputs[0]+" <index>");
            return;
        }
        int index;
        try{
            index = Integer.parseInt(userInputs[1])-1;
        } catch(NumberFormatException e){
            System.out.println("Index must be a number.");
            return;
        }
        boolean done = userInputs[0].equals("mark");
        if (index < 0 || index >= tasks.size()) {
            System.out.println("No such task exists!");
            return;
        }
        tasks.get(index).setDone(done);
        System.out.println((done ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n")
                + tasks.get(index).toString()
        );
    }
    public void handleBye(Scanner scanner) {
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        exit(-1);
    }
    public void deleteTask(String[] input) throws DukeException{
        if(input.length != 2) {
            throw new DukeException("Usage: delete <index>");
        }
        int index;
        try{
            index = Integer.parseInt(input[1])-1;
            System.out.println(index);
        } catch(NumberFormatException e){
            System.out.println("Index must be a number.");
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
        System.out.println("Now you have "+tasks.size()+" tasks in the list");
    }
    private void run() throws DukeException{
        System.out.println("Hello! I'm " + this.name + "\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine().trim();
            String[] userInputs = input.split("\\s+",2);
            String cmd = userInputs[0].toLowerCase();
            switch(cmd) {
                case "bye" -> handleBye(scanner);
                case "list" -> printList();
                case "mark","unmark" -> handleTask(userInputs);
                case "todo" -> addTask(userInputs,taskTypes.TODO);
                case "event"-> addTask(userInputs,taskTypes.EVENT);
                case "deadline"-> addTask(userInputs,taskTypes.DEADLINE);
                case "delete" -> deleteTask(userInputs);
                default -> System.out.println("Invalid Command: " + cmd);
            }
        }
    }
    public static void main(String[] args) {
        try {
            new Duke("Sophia").run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
