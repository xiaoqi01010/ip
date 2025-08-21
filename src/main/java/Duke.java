import java.util.Scanner;
import java.util.*;
//used ChatGpt for quality check of code
public class Duke {
    //Keep a list of internal tasks
    private final List<Task> tasks = new ArrayList<>();
    private final String name;
    public Duke(String name) {
        this.name = name;
    }
    private void printList(){
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1+". " + tasks.get(i).toString());
        }
    }
    private void addTask(String input,String Type){
        Task new_task;
        //System.out.print(Type);
        switch (Type) {
            case "todo" -> {
                new_task = new TodoTask(input);
                if(input.isBlank()){
                    System.out.println("The description of a todo cannot be empty.");
                    return;
                }
            }
            case "deadline" -> {
                String[] task_info = input.split("/by ");
                //System.out.println(Arrays.toString(task_info));
                if(input.isBlank() || task_info.length == 1){
                    System.out.println("The description or date of a deadline task cannot be empty.");
                    return;
                }
                new_task = new DeadlineTask(task_info[0],task_info[task_info.length - 1]);
            }
            case "event" -> {
                String[] task_info = input.split("/");
                String[] from = task_info[task_info.length - 2].split("from ");
                String[] to = task_info[task_info.length - 1].split("to ");

                if(input.isBlank() || from.length == 1 || to.length == 1){
                    System.out.println("The description or start date or end date of an event task cannot be empty.");
                    return;
                }
                new_task = new EventTask(task_info[0],from[1],to[1]);
            }
            default -> {
                System.out.println("Invalid Type");
                return;
            }
        }
        //System.out.print(ch);
        System.out.println("added: " + input);
        tasks.add(new_task);
        System.out.println(new_task);
        System.out.println("Now you have "+tasks.size()+" tasks in the list");
    }
    public void handleTask(int index, boolean done){
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
    public void deleteTask(int index){
        if (index < 0 || index >= tasks.size()) {
            System.out.println("No such task exists!");
            return;
        }
        tasks.remove(index);
        System.out.println("Noted. I've removed this task:\n"
                + tasks.get(index).toString()
        );
        System.out.println("Now you have "+tasks.size()+" tasks in the list");
    }
    private void run() {
        System.out.println("Hello! I'm " + this.name + "\nWhat can I do for you you?");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine().trim();
            String[] userInputs = input.split("\\s+",2);
            String cmd = userInputs[0].toLowerCase();
            switch(cmd) {
                case "bye" -> {
                    scanner.close();
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                }
                case "list" -> printList();
                case "mark","unmark" -> {
                    if(userInputs.length != 2) {
                        System.out.println("Usage: "+cmd+" <index>");
                        break;
                    }
                    int index;
                    try{
                        index = Integer.parseInt(userInputs[1])-1;
                    } catch(NumberFormatException e){
                        System.out.println("Index must be a number.");
                        break;
                    }
                    boolean done = cmd.equals("mark");
                    handleTask(index,done);
                }
                case "event","todo","deadline" -> {
                    if(userInputs.length != 2) {
                        System.out.println("Usage: "+cmd+" <description>");
                        break;
                    }
                    addTask(userInputs[1],userInputs[0]);
                }
                case "delete" -> {
                    if(userInputs.length != 2) {
                        System.out.println("Usage: "+cmd+" <index>");
                    }
                    int index;
                    try{
                        index = Integer.parseInt(userInputs[1])-1;
                    } catch(NumberFormatException e){
                        System.out.println("Index must be a number.");
                        break;
                    }
                    deleteTask(index);
                }
                default -> {
                    System.out.println("Invalid Command: " + cmd);
                }
            }
        }
    }
    public static void main(String[] args) {
            new Duke("Sophia").run();
    }
}
