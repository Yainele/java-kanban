import exceptions.ManagerSaveException;
import manager.taskManager.FileBackedTasksManager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.Type;

import java.nio.file.Path;

import static tasks.Type.TASK;

public class Main {
    public static void main(String[] args) throws ManagerSaveException {
        Path path = Path.of("/Users/daniilkorkacev/dev/java-kanban/data.csv");

        //Создание зададч и заполнение файла
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(path.toString());

        Epic epic1 = new Epic("Epic 1", "desc 1", Type.EPIC);
        fileBackedTasksManager.createEpicTask(epic1);

        Subtask subtask1 = new Subtask("subtask 1", "subtask desc", epic1.getId(), Type.SUBTASK);
        fileBackedTasksManager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("subtask 2", "subtask2 desc", epic1.getId(), Type.SUBTASK);
        fileBackedTasksManager.createSubtask(subtask2);

        Task task1 = new Task("Task1", "Task desc", TASK);
        fileBackedTasksManager.createTask(task1);

        fileBackedTasksManager.getSubTaskById(2);
        fileBackedTasksManager.getSubTaskById(3);
        fileBackedTasksManager.getSubTaskById(2);
        fileBackedTasksManager.getTaskById(4);


        //Менеджер подгружает из все из файла в память и сразу перезаписывает его
        FileBackedTasksManager loadedFileBackedTasksManager = FileBackedTasksManager.loadFromFile(path.toFile());
        System.out.println(loadedFileBackedTasksManager.getEpics() + "\n");
        System.out.println(loadedFileBackedTasksManager.getHistory());

    }
}