import java.util.Scanner;
public class Duke {
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
            }else {
                System.out.println(input);
            }
        }
    }
}
