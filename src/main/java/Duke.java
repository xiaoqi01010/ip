import java.util.Scanner;
import java.util.*;

public class Duke {
    //Keep a list of internal tasks
    private final List<Task> tasks = new ArrayList<>();
    private int count = 0;

    //instantiate
    private void run() throws InvalidIndexException {
        String logo = "Sophia";
        System.out.println("Hello! I'm " + logo + "\nWhat can I do for you you?");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine().trim();
            String[] user_inputs = input.split(" ");
            String cmd = user_inputs[0].toLowerCase();
            switch(cmd) {
                case "bye" -> {
                    scanner.close();
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                }
                case "list" -> {
                    for(Task task : tasks) {
                        System.out.println(task.toString());
                    }
                    break;
                }
                case "mark","unmark" -> {
                    if(user_inputs.length != 2) {
                        System.out.println("Usage: "+cmd+" <index>");
                        break;
                    }
                    int index;
                    try{
                        index = Integer.parseInt(user_inputs[1])-1;
                    }catch(NumberFormatException e){
                        System.out.println("Index must be a number.");
                        break;
                    }
                    if(index < 0 || index >= tasks.size()) {
                        throw new InvalidIndexException("No such task exists!");
                    }
                    boolean done = cmd.equals("mark");
                    tasks.get(index).setDone(done);
                    System.out.println((done ? "Nice! I've marked this task as done:\n"
                            : "OK, I've marked this task as not done yet:\n")
                            + tasks.get(index).toString()
                    );
                    break;
                }
                default -> {
                    tasks.add(new Task(input,++count));
                    System.out.println("added: " + input);
                }
            }
        }
    }
    public static void main(String[] args) {
        try{
            new Duke().run();
        }catch(InvalidIndexException e){
            System.out.println(e.getMessage());
        }
    }
}
