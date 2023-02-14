package manager.taskManager;

import manager.historyManager.HistoryManager;
import manager.Managers;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> taskStorage = new HashMap<>();
    private final HashMap<Integer, Epic> epicStorage = new HashMap<>();
    private final HashMap<Integer, Subtask> subtaskStorage = new HashMap<>();

    HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
    @Override
    public void createTask(Task task){
        taskStorage.put(task.getId(),task);
    }

    @Override
    public void createEpicTask(Epic epic){
        epicStorage.put(epic.getId(),epic);
    }

    @Override
    public void createSubtask(Subtask subtask){
        Epic epic = epicStorage.get(subtask.getEpicId());

        subtaskStorage.put(subtask.getId(),subtask);
        epic.getSubtasksInEpic().add(subtask);
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.add(taskStorage.get(id));
        return taskStorage.get(id);
    }

    @Override
    public Subtask getSubTaskById(int id) {
        historyManager.add(subtaskStorage.get(id));
        return subtaskStorage.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = epicStorage.get(id);
        updateStatus(epic);
        historyManager.add(epicStorage.get(id));
        return epicStorage.get(id);
    }

    @Override
    public void removeTaskById(int id) {
        taskStorage.remove(id);
    }

    @Override
    public void removeEpicById(int id){
        Epic epic = epicStorage.get(id);

        for (Subtask subtask : epic.getSubtasksInEpic()) {
            if(subtask.getEpicId() == epic.getId()){
                subtaskStorage.remove(subtask.getId());
            }
        }
        epic.getSubtasksInEpic().clear();
        epicStorage.remove(id);
    }

    @Override
    public void removeSubTaskById(int id){
        Subtask subtask = subtaskStorage.get(id);
        Epic epic = epicStorage.get(subtask.getEpicId());

        for (Subtask subtaskInEpic : epic.getSubtasksInEpic()) {
            if(subtaskInEpic.getId() == id){
                epic.getSubtasksInEpic().remove(subtaskInEpic);
                break;
            }
        }
        updateStatus(epic);
        subtaskStorage.remove(id);
    }

    @Override
    public void removeAllTasks(){
        taskStorage.clear();
    }

    @Override
    public void removeAllEpics(){
        epicStorage.clear();
        subtaskStorage.clear();
    }

    @Override
    public void removeAllSubtasks(){
        for (Epic epic : epicStorage.values()) {
            epic.getSubtasksInEpic().clear();
            updateStatus(epic);
        }
        subtaskStorage.clear();

    }
    private void updateStatus(Epic epic){
        boolean isEpicDone = true;
        boolean isEpicNew = true;

        for (Subtask subtask : epic.getSubtasksInEpic()) {
            if(subtask.getStatus() != Status.DONE){
                isEpicDone = false;
                break;
            }
        }

        for (Subtask subtask : epic.getSubtasksInEpic()) {
            if(subtask.getStatus() != Status.NEW){
                isEpicNew = false;
                break;
            }
        }

        if(epic.getSubtasksInEpic().isEmpty()){
            epic.setStatus(Status.NEW);
        } else if (isEpicNew) {
            epic.setStatus(Status.NEW);
        } else if (isEpicDone) {
            epic.setStatus(Status.DONE);
        }
        else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    @Override
    public void updateTask(Task task){
        taskStorage.put(task.getId(),task);
    }

    @Override
    public void updateEpic(Epic epic){
        Epic oldEpic = epicStorage.get(epic.getId());
        epic.setSubtasksInEpic(oldEpic.getSubtasksInEpic());
        updateStatus(epic);
        epicStorage.put(epic.getId(), epic);
    }

    @Override
    public void updateSubtask(Subtask subtask){
        Epic epic = epicStorage.get(subtask.getEpicId());

        for (int i = 0; i < epic.getSubtasksInEpic().size(); i++) {
            if(epic.getSubtasksInEpic().get(i).getId() == subtask.getId()){
                epic.getSubtasksInEpic().set(i,subtask);
                break;
            }
        }
        updateStatus(epic);
        subtaskStorage.put(subtask.getId(),subtask);
    }

    @Override
    public List<Task> getTasks(){return new ArrayList<>(taskStorage.values());}

    @Override
    public List<Epic> getEpics(){
        return new ArrayList<>(epicStorage.values());
    }

    @Override
    public List<Subtask> getSubtasks(){
        return new ArrayList<>(subtaskStorage.values());
    }

    @Override
    public List<Subtask> getSubtasksByEpic(Integer id){
        Epic epic = epicStorage.get(id);
        return epic.getSubtasksInEpic();
    }
}
