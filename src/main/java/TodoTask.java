public final class TodoTask extends Task{
    public TodoTask(String input){
        super(input);
    }
    @Override
    public String toString(){
        return "[T]"+super.toString();
    }
}
