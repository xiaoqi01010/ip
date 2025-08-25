package sophia;
import sophia.Parser;
import sophia.SophiaException;

public class TodoTaskParser implements Parser {
    String arguments;
    public TodoTaskParser(String arguments) {
        this.arguments = arguments;
    }
    @Override
    public Task parse() throws SophiaException {
        if(this.arguments.isBlank()) throw new SophiaException("Usage: todo <description>");
        return new TodoTask(this.arguments.trim());
    }
}
