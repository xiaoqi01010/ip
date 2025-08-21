public class Task {
    private String name = "undefined";
    private int idx = -1;
    private boolean done = false;
    public Task(String name, int idx) {
        this.name = name;
        this.idx = idx;
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
            return this.idx + ". [X] " + this.name;
        }else {
            return this.idx + ". [ ] " + this.name;
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