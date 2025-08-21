public final class EventTask extends Task {
    private String startDate,endDate;
    public EventTask(String input, char Type, String startDate, String endDate){
        super(input, Type);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String toString(){
        return super.toString() + "(from: "+startDate+"to: "+endDate+")";
    }
}
