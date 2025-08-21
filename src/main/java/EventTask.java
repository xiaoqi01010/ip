public final class EventTask extends Task {
    private String startDate,endDate;
    public EventTask(String input, String startDate, String endDate){
        super(input);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String toString(){
        return "[E]"+super.toString() + "(from: "+startDate+"to: "+endDate+")";
    }
}
