package tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    private List<Subtask> subtasksInEpic = new ArrayList<>();

    public Epic(String name, String description, Type type) {
        super(name, description, type);
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // проверяем адреса объектов
        if (obj == null) return false; // проверяем ссылку на null
        if (this.getClass() != obj.getClass()) return false; // сравниваем классы
        Epic epic = (Epic) obj; // открываем доступ к полям другого объекта
        return Objects.equals(getName(), epic.getName()) && // проверяем все поля
                Objects.equals(type, epic.type) && // нужно логическое «и»
                Objects.equals(getDescription(), epic.getDescription()) &&
                Objects.equals(status, epic.status) &&
                (Objects.equals(getId(), epic.getId()));
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getName(),
                getDescription(),
                status, type);
    }
}
