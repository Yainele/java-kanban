public class Task {
    private final String name;
    private String description;
    private final Integer id;
    protected Status status;
    enum Status {NEW, IN_PROGRESS, DONE};

    public Task(String name, String description, Status status, Integer id) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public Task(String name, String description, Integer id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public Task(String name, Status status, Integer id) {
        this.name = name;
        this.status = status;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}
