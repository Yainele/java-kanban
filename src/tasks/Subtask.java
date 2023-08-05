package tasks;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // проверяем адреса объектов
        if (obj == null) return false; // проверяем ссылку на null
        if (this.getClass() != obj.getClass()) return false; // сравниваем классы
        Subtask subtask = (Subtask) obj; // открываем доступ к полям другого объекта
        return Objects.equals(getName(), subtask.getName()) && // проверяем все поля
                Objects.equals(type, subtask.type) && // нужно логическое «и»
                Objects.equals(getDescription(), subtask.getDescription()) &&
                Objects.equals(status, subtask.status) &&
                (Objects.equals(getId(), subtask.getId()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getName(),
                getDescription(),
                status, type);
    }
}
