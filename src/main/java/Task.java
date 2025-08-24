import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Task {
    private final String name;
    private boolean done = false;
    public Task(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public abstract void write(BufferedWriter bw) throws IOException;

    @Override
    public String toString() {
        if(this.done){
            return "[X] " + this.name;
        }else {
            return "[ ] " + this.name;
        }
    }

    public void setDone(boolean done){
        this.done = done;
    }

    public boolean isDone(){
        return this.done;
    }

}
//Inner variables