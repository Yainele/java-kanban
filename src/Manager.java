import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    HashMap<Integer, Task> taskStorage = new HashMap<>();
    HashMap<Integer, Epic> epicStorage = new HashMap<>();
    HashMap<Integer, Subtask> subtaskStorage = new HashMap<>();
    int id = 0;
    int commonID = 0;
    public Integer generateID(){return  ++id;}

    public void createTask(Task task){
        taskStorage.put(task.getId(),task);
    }

    public void createEpicTask(Epic epic){
        epicStorage.put(epic.getId(),epic);
        commonID = id;
    }

    public void createSubtask(Subtask subtask){
        Epic epic = epicStorage.get(commonID);
        subtask.setEpicID(epic.getId());
        subtaskStorage.put(subtask.getId(),subtask);
        epic.subtasksInEpic.add(subtask);
    }

    public Task getTaskByID(int id) {
        return taskStorage.get(id);
    }

    public Subtask getSubTaskByID(int id) {
        return subtaskStorage.get(id);
    }

    public Epic getEpicByID(int id) {
        return epicStorage.get(id);
    }

    public void removeTaskByID(int id) {
        taskStorage.remove(id);
    }

    public void removeEpicByID(int id){
        Epic epic = epicStorage.get(id);
        epic.subtasksInEpic.clear();
        epicStorage.remove(id);
    }

    public void removeSubTaskByID(int id){
        Subtask subtask = subtaskStorage.get(id);
        Epic epic = epicStorage.get(subtask.epicID);

        for (Subtask subtaskInEpic : epic.subtasksInEpic) {
            if(subtaskInEpic.getId() == id){
                epic.subtasksInEpic.remove(subtaskInEpic);
                break;
            }
        }

        subtaskStorage.remove(id);
    }

    public void removeAllTasks(){
        taskStorage.clear();
    }

    public void removeAllEpics(){
        for (Integer eID : epicStorage.keySet()) {
            Epic epic = epicStorage.get(eID);
            epic.subtasksInEpic.clear();
        }
        epicStorage.clear();
        subtaskStorage.clear();
    }

    public void removeAllSubtasks(){
        for (Integer eID : epicStorage.keySet()) {
            Epic epic = epicStorage.get(eID);
            epic.subtasksInEpic.clear();
        }
        subtaskStorage.clear();
    }

    public void updateTask(Task task){
        taskStorage.put(task.getId(),task);
    }

    public void updateEpic(Epic epic){
        Epic oldEpic = epicStorage.get(epic.getId());
        epic.subtasksInEpic = oldEpic.subtasksInEpic;
        epicStorage.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask){
        subtask.setEpicID(subtaskStorage.get(subtask.getId()).getEpicID());
        Epic epic = epicStorage.get(subtask.getEpicID());

        for (int i = 0; i < epic.subtasksInEpic.size(); i++) {
            if(epic.subtasksInEpic.get(i).getId() == subtask.getId()){
                epic.subtasksInEpic.set(i,subtask);
                break;
            }
        }
        subtaskStorage.put(subtask.getId(),subtask);
    }

    public HashMap<Integer,Task> getTasks(){return taskStorage;}

    public HashMap<Integer,Epic> getEpics(){return epicStorage;}

    public HashMap<Integer,Subtask> getSubtasks(){return subtaskStorage;}

    public ArrayList<Subtask> getSubtasksByEpic(Integer id){
        Epic epic = epicStorage.get(id);
        return epic.subtasksInEpic;
    }
}
