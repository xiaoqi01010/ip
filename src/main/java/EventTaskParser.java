import java.util.Arrays;

public class EventTaskParser implements Parser {
    String arguments;
    public EventTaskParser(String arguments) {
        this.arguments = arguments;
    }
    @Override
    public Task parse() throws DukeException {
        if(this.arguments.isBlank()) throw new  DukeException("Usage: event <description> /from <date> /to <date>");
        String[] task_info = this.arguments.split("/");
        System.out.println(Arrays.toString(task_info));
        String[] from = task_info[task_info.length - 2].split("from ");
        String[] to = task_info[task_info.length - 1].split("to ");

        if(from.length <= 1 || to.length <= 1){
            throw new DukeException("The start date or end date of an event task cannot be empty.");
        }
        return new EventTask(task_info[0],from[1],to[1]);
    }
}
