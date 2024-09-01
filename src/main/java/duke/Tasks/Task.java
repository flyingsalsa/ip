package duke.Tasks;
public class Task {
    public String task;
    private boolean done;


    public Task(String s){
        task = s;
        done = false;
    }
    public void complete(){
        done = true;
    }
    public void uncomplete(){
        done = false;
    }
    public String toString() {
        String status;
        if(done) {
            status = "[X] ";
        }
        else {
            status = "[ ] ";
        }
        return status + task;
    }
    public boolean isReal(){
        return task != "";
    }

}
