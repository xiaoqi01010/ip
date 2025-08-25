package sophia;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public abstract class Task {
    private final String name;
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    private static boolean validateDateFormat(String str) {
        return (Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$")).matcher(str).matches();
    }

    public String parseDate(String date) {
        if(!validateDateFormat(date)) {return date;}
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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