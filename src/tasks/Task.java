package tasks;

public class Task {
    private final String name;
    private String description;
    private Integer id;
    private static Integer generator = 0;
    protected Status status;


    public Task(String name, String description) {
        this.id = generateId();
        this.status = Status.NEW;
        this.name = name;
        this.description = description;
    }

    public Task(String name) {
        this.id = generateId();
        this.status = Status.NEW;
        this.name = name;
    }

    private Integer generateId(){
        generator++;
        return this.id = generator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if(status == null){
            return Status.NEW;
        }
        return status;
    }
    @Override
    public String toString() {
        return  "Task{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}
