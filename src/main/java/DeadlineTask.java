public final class DeadlineTask extends Task{
    private String ddl;
    public DeadlineTask(String input, String ddl){
        super(input);
        this.ddl = ddl;
    }
    @Override
    public String toString(){
        return "[D]"+super.toString() + "(by: "+ddl+")";
    }
}
