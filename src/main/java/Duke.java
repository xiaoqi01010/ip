import java.util.Scanner;
import java.util.*;

public class Duke {
    //Keep a list of internal tasks
    private final List<Task> tasks = new ArrayList<>();
    private int count = 0;
    private String logo = "Sophia";

    //instantisate
    private void run() throws InvalidIndexException {
        System.out.println("Hello! I'm " + logo + "\nWhat can I do for you you?");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            String[] user_inputs = input.split(" ");
            if(input.equals("bye")) {
                scanner.close();
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }else if(input.equals("list")) {
                for(Task task : tasks) {
                    System.out.println(task.toString());
                }
            } else if(user_inputs[0].equals("mark")) {
                int index =  Integer.parseInt(user_inputs[1]) - 1;
                if(index < 0 || index >= tasks.size()) {
                    throw new InvalidIndexException("Invalid task index");
                }
                tasks.get(index).setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(index).toString());
            }else if(user_inputs[0].equals("unmark")) {
                int index =  Integer.parseInt(user_inputs[1]) - 1;
                if(index < 0 || index >= tasks.size()) {
                    throw new InvalidIndexException("Invalid task index");
                }
                tasks.get(index).setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(index).toString());
            }
            else {
                tasks.add(new Task(input,++count));
                System.out.println("added: " + input);
            }
        }
    }
    public static void main(String[] args) throws InvalidIndexException {
        try{
            new Duke().run();
        }catch(InvalidIndexException e){
            System.out.println(e.getMessage());
        }
    }
}
