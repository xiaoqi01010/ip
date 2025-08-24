public class EventTaskParser implements Parser {
    String arguments;
    public EventTaskParser(String arguments) {
        this.arguments = arguments;
    }
    @Override
    public Task parse() throws SohpiaException {
        String[] task_info = this.arguments.split("/");
        //System.out.println(Arrays.toString(task_info));
        String from = task_info[task_info.length - 2].split("from ")[1].trim();
        String to = task_info[task_info.length - 1].split("to ")[1].trim();
        return new EventTask(task_info[0].trim(),from,to);
    }
}
