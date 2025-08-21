public class EventTask extends Task {
    private String startDate,endDate;
    public EventTask(String input, int idx, char Type, String startDate, String endDate){
        super(input, idx, Type);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String toString(){
        return super.toString() + "(from: "+startDate+"to: "+endDate+")";
    }
}
