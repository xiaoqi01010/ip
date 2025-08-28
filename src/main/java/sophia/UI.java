package sophia;

import java.util.List;


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
    public String showError(Exception e) {
        return e.getMessage();
    }

    public String printEmptyMessage() {
        return "Please enter a command";
    }
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints all tasks
     * @param taskList which is a wrapper class of the list of tasks
     */
    public String printList(TaskList taskList) {
        // delegates directly
        return taskList.printList();
    }

    /**
     * UI for adding task to list of tasks
     * @param new_task specifying a new task to be added
     * @param taskList is a wrapper class for list of tasks
     */
    public String addTask(Task new_task, TaskList taskList) {
        return "Got it. I've added this task: "
                + "\n" + new_task + "\n"
                + "Now you have " + taskList.taskListSize()
                + " tasks in the list";
    }

    /**
     * UI for deletion of task
     * @param idx which is index of task to be deleted
     * @param taskList which is the wrapper class of list of tasks
     */
    public String deleteTask(int idx, TaskList taskList) {
        return "Got it. I've deleted this task: "
                + taskList.getTask(idx)
                + "\n"
                + "Now you have "
                + taskList.taskListSize()
                + " tasks in the list";
    }

    public String printTasksFound(List<Task> taskList) {
        StringBuilder str = new StringBuilder();
        for (int j = 0; j < taskList.size(); j++) {
            str.append((j + 1)).append(". ").append(taskList.get(j));
            str.append("\n");
        }
        return str.toString();
    }

    public String markTask(TaskList taskList, boolean done, int index) {
        return (done
                ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n")
                + taskList.getTask(index);
    }

    /**
     * UI for introduction
     * @param name of chatbot
     */
    public String introduction(String name) {
        return "Hello! I'm " + name + "\nWhat can I do for you?";
    }

    public String saved() {
        return "Saved tasks to file";
    }
}
