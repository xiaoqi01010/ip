public class DeadlineTaskParser implements Parser {
    String arguments;
    public DeadlineTaskParser(String arguments) {
        this.arguments = arguments;
    }
    @Override
    public Task parse() throws DukeException {
        if(this.arguments.isBlank()) throw new  DukeException("Usage: deadline <description> /by <date>");
        String[] task_info = this.arguments.split("/by ");
        //System.out.println(Arrays.toString(task_info));
        if(this.arguments.isBlank() || task_info.length <= 1){
            throw new DukeException("The description or date of a deadline task cannot be empty.");
        }
        return new DeadlineTask(task_info[0],task_info[task_info.length - 1]);
    }
}
