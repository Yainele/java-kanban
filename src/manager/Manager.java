package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Manager {
    private final HashMap<Integer, Task> taskStorage = new HashMap<>();
    private final HashMap<Integer, Epic> epicStorage = new HashMap<>();
    private final HashMap<Integer, Subtask> subtaskStorage = new HashMap<>();
    int id = 0;

    private Integer generateID(){return  ++id;}

    public void createTask(Task task){
        task.setId(generateID());
        task.setStatus(Status.NEW);
        taskStorage.put(task.getId(),task);
    }

    public void createEpicTask(Epic epic){
        epic.setId(generateID());
        epicStorage.put(epic.getId(),epic);

    }

    public void createSubtask(Subtask subtask){
        Epic epic = epicStorage.get(subtask.getEpicId());
        subtask.setId(generateID());
        subtask.setStatus(Status.NEW);

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
        }
        subtaskStorage.clear();
    }

    public void updateTask(Task task){
        taskStorage.put(task.getId(),task);
    }

    public void updateEpic(Epic epic){
        Epic oldEpic = epicStorage.get(epic.getId());
        epic.setSubtasksInEpic(oldEpic.getSubtasksInEpic());
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
        subtaskStorage.put(subtask.getId(),subtask);
    }

    public List<Task> getTaskList(){
        List<Task> taskList = new ArrayList<>();
        for (Integer taskId : taskStorage.keySet()) {
            taskList.add(taskStorage.get(taskId));
        }
        return taskList;
    }

    public List<Epic> getEpics(){
        List<Epic> epicList = new ArrayList<>();
        for (Integer epicId : epicStorage.keySet()) {
            epicList.add(epicStorage.get(epicId));
        }
        return epicList;
    }

    public List<Subtask> getSubtasks(){
        List<Subtask> subtaskList = new ArrayList<>();
        for (Integer subtaskId : subtaskStorage.keySet()) {
            subtaskList.add(subtaskStorage.get(subtaskId));
        }
        return subtaskList;
    }

    public List<Subtask> getSubtasksByEpic(Integer id){
        Epic epic = epicStorage.get(id);
        return epic.getSubtasksInEpic();
    }
}