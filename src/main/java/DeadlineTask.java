public class DeadlineTask extends Task{
    private String ddl;
    public DeadlineTask(String input, int idx, char Type, String ddl){
        super(input, idx, Type);
        this.ddl = ddl;
    }
    @Override
    public String toString(){
        return super.toString() + "(by: "+ddl+")";
    }
}
