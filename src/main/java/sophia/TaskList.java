package sophia;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contain a list of tasks
 */
public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public TaskList() {}
    /**
     * Constructor of TaskList
     * <p>
     * @param xs specifies a list of Tasks which are to be added to taskList upon initialisation
     */
    public TaskList(List<Task> xs) throws FileNotFoundException {
        tasks.addAll(xs);
    }

    /**
     * Returns a formatted string of a list of tasks which are overdue/pending
     * @return a formatted string
     */
    public List<String> sendReminder() {
        List<String> reminderList = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof TaskWithDate) {
                TaskWithDate taskWithDate = (TaskWithDate) task;
                if (taskWithDate.isDueWithinThreeDays() && !task.isDone()) {
                    reminderList.add(taskWithDate.sendReminder());
                }
            }
        }
        if (reminderList.isEmpty()) {
            reminderList.add("Woohoo, no task is due soon!");
        }
        //System.out.println(reminderList);
        return reminderList;
    }
    /**
     * Return a list of Tasks with keywords including key
     * @param key which is the keyword
     * @return a list of tasks
     */
    public List<Task> findTask(String key) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().contains(key)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Iterate through each task, allowing each task to write its information into a file using
     * a buffered Writer
     * <p>
     * @param bw specifies a BufferedWriter
     */
    public void write(BufferedWriter bw) throws IOException {
        for (Task task : tasks) {
            task.write(bw);
        }
    }

    /**
     * iterate through every task to print the information of every task
     */
    public String printList() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            str.append((i + 1)).append(". ").append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int taskListSize() {
        return this.tasks.size();
    }

    public void setDone(int index, boolean done) {
        this.tasks.get(index).setDone(done);
    }

    /**
     * Retrieve the respective index_th task and print the string representation
     * @param index index of the task to retrieve
     */
    public String getTask(int index) {
        return this.tasks.get(index).toString();
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }
}
