package sophia;

import java.util.List;
import java.util.regex.Pattern;

public class UI {
    /**
     * Print a line to separate previous output
     */
    public String printLine() {
        return "__________________________________________________";
    }

    /**
     * Shows the error message
     * @param e indicates the error message
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printEmptyMessage() {
        System.out.println("Please enter a command");
    }
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all tasks
     * @param taskList which is a wrapper class of the list of tasks
     */
    public void printList(TaskList taskList) {
        System.out.println(taskList.printList());   // delegates directly
    }

    /**
     * UI for adding task to list of tasks
     * @param new_task specifying a new task to be added
     * @param taskList is a wrapper class for List<Task>
     */
    public void addTask(Task new_task, TaskList taskList) {
        System.out.println("Got it. I've added this task: "
                + "\n" + new_task
                + "Now you have " + taskList.taskListSize()
                + " tasks in the list"
        );
    }

    /**
     * UI for deletion of task
     * @param idx which is index of task to be deleted
     * @param taskList which is the wrapper class of List<Task>
     */
    public void deleteTask(int idx, TaskList taskList) {
        System.out.println("Got it. I've deleted this task: "
                + taskList.getTask(idx)
                + "\n" + printLine()
                + "\nNow you have "
                + taskList.taskListSize()
                + " tasks in the list");
    }

    public void printTasksFound(List<Task> taskList) {
        StringBuilder str = new StringBuilder();
        for (int j = 0; j < taskList.size(); j++) {
            str.append((j + 1)).append(". ").append(taskList.get(j));
        }
        System.out.println(str.toString());
    }

    public void markTask(TaskList taskList, boolean done, int index) {
        System.out.println((done
                ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n")
                + taskList.getTask(index)
        );
    }

    /**
     * UI for introduction
     * @param name of chatbot
     */
    public void introduction(String name) {
        printLine();
        System.out.println("\nHello! I'm " + name + "\nWhat can I do for you?");
    }

    public void saved() {
        System.out.println("Saved tasks to file");
    }
}
