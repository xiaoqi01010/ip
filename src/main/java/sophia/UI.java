package sophia;

import java.util.List;
import java.util.regex.Pattern;

public class UI {
    /**
     * Print a line to separate previous output
     */
    public void printLine() {
        System.out.println("__________________________________________________");
    }

    /**
     * Shows the error message
     * @param e indicates the error message
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all tasks
     * @param taskList which is a wrapper class of the list of tasks
     */
    public void printList(TaskList taskList) {
        taskList.printList();   // delegates directly
    }

    /**
     * UI for adding task to list of tasks
     * @param new_task specifying a new task to be added
     * @param taskList is a wrapper class for List<Task>
     */
    public void addTask(Task new_task, TaskList taskList) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(new_task);
        System.out.println("Now you have " + taskList.taskListSize() + " tasks in the list");
    }

    public String testDeleteTask(int idx, TaskList taskList) {
        return taskList.getTask(idx);
    }

    /**
     * UI for deletion of task
     * @param idx which is index of task to be deleted
     * @param taskList which is the wrapper class of List<Task>
     */
    public void deleteTask(int idx, TaskList taskList) {
        System.out.println("Noted. I've removed this task:\n"
                + taskList.getTask(idx)
        );
        System.out.println("__________________________________________________");
        System.out.println("Now you have " + taskList.taskListSize() + " tasks in the list");
    }

    public void printTasksFound(List<Task> taskList) {
        for (int j = 0; j < taskList.size(); j++) {
            System.out.println( (j + 1) + ". " + taskList.get(j));
        }
    }

    public void markTask(TaskList taskList, boolean done, int index) {
        System.out.println((done ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n")
                + taskList.getTask(index)
        );
    }

    /**
     * UI for introduction
     * @param name of chatbot
     */
    public void introduction(String name) {
        System.out.println("__________________________________________________");
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
    }

    public void saved() {
        System.out.println("Saved tasks to file");
    }
}
