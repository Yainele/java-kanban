public class Subtask extends Task{

    protected int epicID;
    public Subtask(String name, Status status, Integer id) {
        super(name, status, id);
    }

    public int getEpicID() {
        return epicID;
    }

    public void setEpicID(int epicID) {
        this.epicID = epicID;
    }
    @Override
    public String toString() {
        return  "{id=" + getId() +
                ", name='" + getName() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}
