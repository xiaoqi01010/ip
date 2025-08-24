public class TodoTaskParser implements Parser {
    String arguments;
    public TodoTaskParser(String arguments) {
        this.arguments = arguments;
    }
    @Override
    public Task parse() throws DukeException{
        if(this.arguments.isBlank()) throw new DukeException("Usage: todo <description>");
        return new TodoTask(this.arguments.trim());
    }
}
