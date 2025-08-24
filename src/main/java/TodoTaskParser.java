public class TodoTaskParser implements Parser {
    String arguments;
    public TodoTaskParser(String arguments) {
        this.arguments = arguments;
    }
    @Override
    public Task parse() throws SohpiaException {
        if(this.arguments.isBlank()) throw new SohpiaException("Usage: todo <description>");
        return new TodoTask(this.arguments.trim());
    }
}
