public abstract class Task {
    private String name = "undefined";
    private char Type;
    private int idx = -1;
    private boolean done = false;
    public Task(String name, int idx, char type) {
        this.name = name;
        this.idx = idx;
        this.Type = type;
    }
    public String getName(){
        return this.name;
    }
    public int getIdx(){
        return this.idx;
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

    public void setIdx(int idx){
        this.idx = idx;
    }
}
//Inner variables