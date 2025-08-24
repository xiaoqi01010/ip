import java.io.BufferedWriter;
import java.io.IOException;

public final class EventTask extends Task {
    private String startDate,endDate;
    public EventTask(String input, String startDate, String endDate){
        super(input);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void write(BufferedWriter bw) throws IOException {
        bw.write("E | " + (isDone()? 1:0) + " | " + getName() + " | " +startDate + " | " +endDate + "\n");
        bw.flush();
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate+")";
    }
}
