package tasks;

public class Subtask extends Task {

    private int epicId;

    public Subtask(String name, String description, int epicId, Type type) {
        super(name, description, type);
        this.epicId = epicId;

    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
    @Override
    public String toString() {
        return  "SubTask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}
