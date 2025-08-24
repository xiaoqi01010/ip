import java.util.regex.Pattern;

public class UI {
    public void printLine() {
        System.out.println("__________________________________________________");
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printList(TaskList taskList) {
        taskList.printList();   // delegates directly
    }

    public void addTask(Task new_task, TaskList taskList) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(new_task);
        System.out.println("Now you have " + taskList.taskListSize()+ " tasks in the list");
    }

    public void deleteTask(int idx, TaskList taskList) {
        System.out.println("Noted. I've removed this task:\n"
                + taskList.getTask(idx)
        );
        System.out.println("__________________________________________________");
        System.out.println("Now you have "+taskList.taskListSize()+" tasks in the list");
    }

    public void markTask(TaskList taskList, boolean done, int index) {
        System.out.println((done ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n")
                + taskList.getTask(index)
        );
    }

    public void introduction(String name) {
        System.out.println("__________________________________________________");
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
    }

    public void saved() {
        System.out.println("Saved tasks to file");
    }
}
