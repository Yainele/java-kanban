import java.util.ArrayList;
public class Epic extends Task{
    public Epic(String name, String description, Integer id) {
        super(name, description,id);
    }
    ArrayList<Subtask> subtasksInEpic = new ArrayList<>();
    public boolean isEpicDone(){
        boolean isSubTasksDone = true;

        for (Subtask subtask : subtasksInEpic) {
            if(subtask.status != Status.DONE){
                isSubTasksDone = false;
            }
        }
        return isSubTasksDone;
    }
    public boolean isEpicNew(){
        boolean isSubTasksNew = true;
        for (Subtask subtask : subtasksInEpic) {
            if(subtask.status != Status.NEW){
                isSubTasksNew = false;
            }
        }
        return isSubTasksNew;
    }
    @Override
    public Status getStatus() {
        if(subtasksInEpic.isEmpty()){
            this.status = Status.NEW;
        } else if (isEpicNew()) {
            this.status = Status.NEW;
        } else if (isEpicDone()) {
            this.status = Status.DONE;
        }
        else {
            this.status = Status.IN_PROGRESS;
        }
        return this.status;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", SubTasks=" + subtasksInEpic +
                '}';
    }
}
