package manager.taskManager;

import exceptions.ManagerSaveException;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.List;

public interface TaskManager {

    List<Task> getHistory();

    void createTask(Task task) throws ManagerSaveException;

    void createEpicTask(Epic epic) throws ManagerSaveException;

    void createSubtask(Subtask subtask) throws ManagerSaveException;

    Task getTaskById(int id) throws ManagerSaveException;

    Subtask getSubTaskById(int id) throws ManagerSaveException;

    Epic getEpicById(int id) throws ManagerSaveException;

    void removeTaskById(int id) throws ManagerSaveException;

    void removeEpicById(int id) throws ManagerSaveException;

    void removeSubTaskById(int id) throws ManagerSaveException;

    void removeAllTasks() throws ManagerSaveException;

    void removeAllEpics() throws ManagerSaveException;

    void removeAllSubtasks() throws ManagerSaveException;

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    List<Task> getTasks();

    List<Epic> getEpics();

    List<Subtask> getSubtasks();

    List<Subtask> getSubtasksByEpic(Integer id);
}
