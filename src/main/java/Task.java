public abstract class Task {
    private String name = "undefined";
    private boolean done = false;
    public Task(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        if(this.done){
            return "[ ] " + this.name;
        }else {
            return "[X] " + this.name;
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