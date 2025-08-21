public final class DeadlineTask extends Task{
    private String ddl;
    public DeadlineTask(String input, char Type, String ddl){
        super(input, Type);
        this.ddl = ddl;
    }
    @Override
    public String toString(){
        return super.toString() + "(by: "+ddl+")";
    }
}
