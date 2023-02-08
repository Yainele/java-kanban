package tasks;

import manager.Status;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Subtask> subtasksInEpic = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public List<Subtask> getSubtasksInEpic() {
        return subtasksInEpic;
    }

    public void setSubtasksInEpic(List<Subtask> subtasksInEpic) {
        this.subtasksInEpic = subtasksInEpic;
    }

    public boolean isEpicDone(){

        for (Subtask subtask : subtasksInEpic) {
            if(subtask.status != Status.DONE){
                return false;
            }
        }
        return true;
    }
    public boolean isEpicNew(){

        for (Subtask subtask : subtasksInEpic) {
            if(subtask.status != Status.NEW){
                return false;
            }
        }
        return true;
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
        return  "Task{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", SubTasks=" + subtasksInEpic +
                '}';
    }
}
