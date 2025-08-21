import java.util.Scanner;
import java.util.*;
public class Duke {
    private static class Task {
        private String name = "undefined";
        private int idx = -1;
        private boolean done = false;
        public Task(String name, int idx) {
            this.name = name;
            this.idx = idx;
        }
        public String getName(){
            return this.name;
        }
        public int getIdx(){
            return this.idx;
        }
        public String show() {
            if(this.done){
                return this.idx + ". [X] " + this.name;
            }else {
                return this.idx + ". [ ] " + this.name;
            }
        }
    }
    static List<Task> tasks = new ArrayList<>();
    static int count = 0;
    public static void main(String[] args) {
        String logo = "Sophia";
        System.out.println("Hello! I'm " + logo + "\nWhat can I do for you you?");
        Scanner scn = new Scanner(System.in);
        while(true) {
            String input = scn.nextLine();
            if(input.equals("bye")) {
                scn.close();
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }else if(input.equals("list")) {
                for(Task task : tasks) {
                    System.out.println(task.show());
                }
            } else if(input.contains("mark")) {

            }else {
                tasks.add(new Duke.Task(input,++count));
                System.out.println("added: " + input);
            }
        }
    }
}
