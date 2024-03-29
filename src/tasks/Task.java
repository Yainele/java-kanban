package tasks;

import java.util.Objects;

public class Task {
    private final String name;
    private String description;
    private Integer id;
    private static Integer generator = 0;
    protected Status status;
    protected Type type;


    public Task(String name, String description, Type type) {
        this.id = generateId();
        this.status = Status.NEW;
        this.type = type;
        this.name = name;
        this.description = description;

    }

    public Task(String name, Type type) {
        this.id = generateId();
        this.status = Status.NEW;
        this.type = type;
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
    public Type getType(){
        return type;
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

    @Override
    public int hashCode() {
        return Objects.hash(id,
                name,
                description,
                status, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // проверяем адреса объектов
        if (obj == null) return false; // проверяем ссылку на null
        if (this.getClass() != obj.getClass()) return false; // сравниваем классы
        Task task = (Task) obj; // открываем доступ к полям другого объекта
        return Objects.equals(name, task.name) && // проверяем все поля
                Objects.equals(type, task.type) && // нужно логическое «и»
                Objects.equals(description, task.description) &&
                Objects.equals(status, task.status) &&
                (Objects.equals(id, task.id));
    }

}
