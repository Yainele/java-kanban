package manager.taskManager;

import exceptions.ManagerSaveException;
import manager.historyManager.HistoryManager;
import tasks.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static tasks.Type.*;


public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    private static Path path;

    public FileBackedTasksManager(String path) {
        FileBackedTasksManager.path = Paths.get(path);
    }

    private void save() throws ManagerSaveException {
        String header = "id,type,name,status,description,epic\n";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path.toFile(), StandardCharsets.UTF_8))) {
            bufferedWriter.write(header);

            for (Task task : getTasks()) {
                bufferedWriter.write(taskToString(task));
            }
            for (Epic epic : getEpics()) {
                bufferedWriter.write(taskToString(epic));
            }
            for (Subtask subtask : getSubtasks()) {
                bufferedWriter.write(taskToString(subtask));
            }
            bufferedWriter.write(historyToString(historyManager));
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка сохранения!");
        }
    }

    private String taskToString(Task task) {

        String value = task.getId() + "," +
                task.getType() + "," +
                task.getName() + "," +
                task.getStatus() + "," +
                task.getDescription();

        if (task.getType() == Type.SUBTASK) {
            value = value + "," + subtaskStorage.get(task.getId()).getEpicId() + "\n";
        } else {
            value = value + "\n";
        }

        return value;
    }

    private static String historyToString(HistoryManager manager) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : manager.getHistory()) {
            stringBuilder.append(task.getId()).append(",");
        }

        if (stringBuilder.length() != 0) {
            stringBuilder.insert(0, "\n");
            stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
        }
        return stringBuilder.toString();
    }

    public static FileBackedTasksManager loadFromFile(File file) throws ManagerSaveException {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(file.toString());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                loadTasks(bufferedReader, fileBackedTasksManager);
                loadHistory(bufferedReader, fileBackedTasksManager);
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка загрузки!");
        }
        return fileBackedTasksManager;
    }

    private static void loadHistory(BufferedReader bufferedReader, FileBackedTasksManager fileBackedTasksManager) throws ManagerSaveException {
        try {
            while (bufferedReader.ready()) {
                String data = bufferedReader.readLine();
                if (data != null) {
                    for (Integer integer : historyFromString(data)) {

                        if (fileBackedTasksManager.getTaskStorage().get(integer) != null) {

                            fileBackedTasksManager.getHistoryManager().add(fileBackedTasksManager.getTaskStorage().get(integer));
                        } else if (fileBackedTasksManager.getEpicStorage().get(integer) != null) {

                            fileBackedTasksManager.getHistoryManager().add(fileBackedTasksManager.getEpicStorage().get(integer));
                        } else if (fileBackedTasksManager.getSubtaskStorage().get(integer) != null) {

                            fileBackedTasksManager.getHistoryManager().add(fileBackedTasksManager.getSubtaskStorage().get(integer));
                        }

                    }
                }
                fileBackedTasksManager.save();
            }

        } catch (IOException e) {
            throw new ManagerSaveException("Error");
        }
    }


    private static void loadTasks(BufferedReader bufferedReader, FileBackedTasksManager fileBackedTasksManager) throws ManagerSaveException {
        try {
            while (bufferedReader.ready()) {
                String data = bufferedReader.readLine();
                if (data.isBlank() || data.isEmpty()) {
                    return;
                }
                String[] split = data.split(",");
                switch (split[1]) {
                    case "TASK":
                        fileBackedTasksManager.createTask(taskFromString(data));
                        break;
                    case "EPIC":
                        fileBackedTasksManager.createEpicTask(epicFromString(data));
                        break;
                    case "SUBTASK":
                        fileBackedTasksManager.createSubtask(subtaskFromString(data));
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Error");
        }
    }

    private static List<Integer> historyFromString(String value) {
        List<Integer> history = new ArrayList<>();
        if (value != null && !value.isBlank() && !value.isEmpty()) {
            String[] split = value.split(",");
            for (String ids : split) {
                history.add(Integer.parseInt(ids));
            }
        }
        return history;
    }

    private static Task taskFromString(String value) {
        String[] split = value.split(",");
        Task task = new Task(split[2], split[4], TASK);
        task.setId(Integer.parseInt(split[0]));
        task.setStatus(Status.valueOf(split[3]));
        return task;
    }

    private static Epic epicFromString(String value) {
        String[] split = value.split(",");
        Epic epic = new Epic(split[2], split[4], EPIC);
        epic.setId(Integer.parseInt(split[0]));
        epic.setStatus(Status.valueOf(split[3]));
        return epic;
    }

    private static Subtask subtaskFromString(String value) {
        String[] split = value.split(",");
        Subtask subtask = new Subtask(split[2], split[4], Integer.parseInt(split[5]), SUBTASK);
        subtask.setId(Integer.parseInt(split[0]));
        subtask.setStatus(Status.valueOf(split[3]));
        return subtask;
    }

    @Override
    public void createTask(Task task) throws ManagerSaveException {
        super.createTask(task);
        save();
    }

    @Override
    public void createEpicTask(Epic epic) throws ManagerSaveException {
        super.createEpicTask(epic);
        save();
    }

    @Override
    public void createSubtask(Subtask subtask) throws ManagerSaveException {
        super.createSubtask(subtask);
        save();
    }

    @Override
    public void removeTaskById(int id) throws ManagerSaveException {
        super.removeTaskById(id);
        save();
    }

    @Override
    public void removeEpicById(int id) throws ManagerSaveException {
        super.removeEpicById(id);
        save();
    }

    @Override
    public void removeSubTaskById(int id) throws ManagerSaveException {
        super.removeSubTaskById(id);
        save();
    }

    @Override
    public void removeAllTasks() throws ManagerSaveException {
        super.removeAllTasks();
        save();
    }

    @Override
    public void removeAllEpics() throws ManagerSaveException {
        super.removeAllEpics();
        save();
    }

    @Override
    public void removeAllSubtasks() throws ManagerSaveException {
        super.removeAllSubtasks();
        save();
    }

    @Override
    public Task getTaskById(int id) throws ManagerSaveException {
        Task task = super.getTaskById(id);
        save();
        return task;
    }

    @Override
    public Subtask getSubTaskById(int id) throws ManagerSaveException {
        Subtask subtask = super.getSubTaskById(id);
        save();
        return subtask;
    }

    @Override
    public Epic getEpicById(int id) throws ManagerSaveException {
        Epic epic = super.getEpicById(id);
        save();
        return epic;
    }

}
