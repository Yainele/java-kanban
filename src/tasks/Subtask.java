package tasks;

public class Subtask extends Task {

    private int epicId;
    public Subtask(String name) {
        super(name);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
    @Override
    public String toString() {
        return  "{id=" + getId() +
                ", name='" + getName() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}
