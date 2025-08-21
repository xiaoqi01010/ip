import java.util.Scanner;
import java.util.*;
//used ChatGpt for quality check of code
public class Duke {
    //Keep a list of internal tasks
    private final List<Task> tasks = new ArrayList<>();
    private String name = null;
    public Duke(String name) {
        this.name = name;
    }
    private void printList(){
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1+". " + tasks.get(i).toString());
        }
    }
    private void addTask(String input,String Type){
        char ch = ' ';
        Task new_task = null;
        //System.out.print(Type);
        if(Type.equals("todo")){
            ch = 'T';
            new_task = new TodoTask(input,ch);
        }else if(Type.equals("deadline")){
            ch = 'D';
            String[] task_info = input.split("/by ");
            new_task = new DeadlineTask(task_info[0],ch,task_info[task_info.length-1]);
        } else if(Type.equals("event")){
            ch = 'E';
            String[] task_info = input.split("/");
            String from = task_info[task_info.length-2].split("from ")[1];
            String to = task_info[task_info.length-1].split("to ")[1];
            new_task = new EventTask(task_info[0],ch,from,to);
        } else {
            System.out.println("Invalid Type");
            return;
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
                case "list" -> {
                    printList();
                    break;
                }
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
                    break;
                }
                default -> {
                    addTask(userInputs[1],userInputs[0]);
                }
            }
        }
    }
    public static void main(String[] args) {
            new Duke("Sophia").run();
    }
}
