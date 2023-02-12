package manager;

import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Manager {
    private final HashMap<Integer, Task> taskStorage = new HashMap<>();
    private final HashMap<Integer, Epic> epicStorage = new HashMap<>();
    private final HashMap<Integer, Subtask> subtaskStorage = new HashMap<>();


    public void createTask(Task task){
        taskStorage.put(task.getId(),task);
    }

    public void createEpicTask(Epic epic){
        epicStorage.put(epic.getId(),epic);

    }

    public void createSubtask(Subtask subtask){
        Epic epic = epicStorage.get(subtask.getEpicId());

        subtaskStorage.put(subtask.getId(),subtask);
        epic.getSubtasksInEpic().add(subtask);
    }

    public Task getTaskById(int id) {
        return taskStorage.get(id);
    }

    public Subtask getSubTaskById(int id) {
        return subtaskStorage.get(id);
    }

    public Epic getEpicById(int id) {
        Epic epic = epicStorage.get(id);
        updateStatus(epic);
        return epicStorage.get(id);
    }

    public void removeTaskById(int id) {
        taskStorage.remove(id);
    }

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

    public void removeAllTasks(){
        taskStorage.clear();
    }

    public void removeAllEpics(){
        epicStorage.clear();
        subtaskStorage.clear();
    }

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

    public void updateTask(Task task){
        taskStorage.put(task.getId(),task);
    }

    public void updateEpic(Epic epic){
        Epic oldEpic = epicStorage.get(epic.getId());
        epic.setSubtasksInEpic(oldEpic.getSubtasksInEpic());
        updateStatus(epic);
        epicStorage.put(epic.getId(), epic);
    }

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

    public List<Task> getTasks(){
        return new ArrayList<>(taskStorage.values());
    }

    public List<Epic> getEpics(){
        return new ArrayList<>(epicStorage.values());
    }

    public List<Subtask> getSubtasks(){
        return new ArrayList<>(subtaskStorage.values());
    }

    public List<Subtask> getSubtasksByEpic(Integer id){
        Epic epic = epicStorage.get(id);
        return epic.getSubtasksInEpic();
    }
}
