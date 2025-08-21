public abstract class Task {
    private String name = "undefined";
    private char Type;
    private boolean done = false;
    public Task(String name, char type) {
        this.name = name;
        this.Type = type;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        if(this.done){
            return "["+this.Type+"][ ] " + this.name;
        }else {
            return "["+this.Type+"][X] " + this.name;
        }
    }

    public void setDone(boolean done){
        this.done = done;
    }

    public boolean isDone(){
        return this.done;
    }

    public void setName(String name){
        this.name = name;
    }
}
//Inner variables