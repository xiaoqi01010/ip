import java.util.Scanner;
import java.util.*;
public class Duke {
    static List<String> tasks = new ArrayList<>();
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
                int idx = 1;
                for(String s : tasks) {
                    System.out.println(idx + ". " + s);
                    idx+=1;
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
