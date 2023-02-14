package tasks;

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
    @Override
    public String toString() {
        return  "EpicTask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", SubTasks=" + subtasksInEpic +
                '}';
    }
}
