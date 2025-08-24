import java.io.BufferedWriter;
import java.io.IOException;

public final class DeadlineTask extends Task{
    private String ddl;
    public DeadlineTask(String input, String ddl){
        super(input);
        this.ddl = ddl;
    }

    @Override
    public void write(BufferedWriter bw) throws IOException {
        bw.write("E | " + (isDone()? 1:0) + " | " + getName() + " | " +ddl + "\n");
        bw.flush();
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + ddl + " )";
    }
}
