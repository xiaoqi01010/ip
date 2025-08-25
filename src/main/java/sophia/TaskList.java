package sophia;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> xs) throws FileNotFoundException {
        tasks.addAll(xs);
    }

    public void write(BufferedWriter bw) throws IOException {
        for (Task task : tasks) {
            task.write(bw);
        }
    }

    public TaskList() {}

    public void printList() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int taskListSize() {
        return this.tasks.size();
    }

    public void setDone(int index,boolean done) {
        this.tasks.get(index).setDone(done);
    }

    public String getTask(int index) {
        return this.tasks.get(index).toString();
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }
}
