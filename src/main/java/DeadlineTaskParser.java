public class DeadlineTaskParser implements Parser {
    String arguments;
    public DeadlineTaskParser(String arguments) {
        this.arguments = arguments;
    }
    @Override
    public Task parse() throws SophiaException {
        String[] task_info = this.arguments.split("/by ");
        return new DeadlineTask(task_info[0].trim(),task_info[task_info.length - 1].trim());
    }
}
