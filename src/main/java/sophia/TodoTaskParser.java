package sophia;
import sophia.Parser;
import sophia.SophiaException;

public class TodoTaskParser implements Parser {
    String arguments;
    public TodoTaskParser(String arguments) {
        this.arguments = arguments;
    }

    /**
     *
     * @return a TodoTask by invoking TodoTask constructor
     * @throws SophiaException if the argument is blank
     */
    @Override
    public Task parse() throws SophiaException {
        if(this.arguments.isBlank()) throw new SophiaException("Usage: todo <description>");
        return new TodoTask(this.arguments.trim());
    }
}
