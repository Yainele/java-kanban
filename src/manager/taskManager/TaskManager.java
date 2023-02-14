package manager.taskManager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.List;

public interface TaskManager {

    List<Task> getHistory();

    void createTask(Task task);

    void createEpicTask(Epic epic);

    void createSubtask(Subtask subtask);

    Task getTaskById(int id);

    Subtask getSubTaskById(int id);

    Epic getEpicById(int id);

    void removeTaskById(int id);

    void removeEpicById(int id);

    void removeSubTaskById(int id);

    void removeAllTasks();

    void removeAllEpics();

    void removeAllSubtasks();

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    List<Task> getTasks();

    List<Epic> getEpics();

    List<Subtask> getSubtasks();

    List<Subtask> getSubtasksByEpic(Integer id);
}
