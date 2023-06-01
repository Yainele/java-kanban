import manager.Managers;
import manager.taskManager.TaskManager;
import tasks.Epic;
import tasks.Subtask;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefault();

        Epic firstEpic = new Epic("Эпик 1", "Описание 1");
        taskManager.createEpicTask(firstEpic);

        Subtask FirstSubtaskFor1Epic = new Subtask("Первая подзадача", firstEpic.getId());
        taskManager.createSubtask(FirstSubtaskFor1Epic);

        Subtask SecondSubtaskFor1Epic = new Subtask("Вторая подзадача", firstEpic.getId());
        taskManager.createSubtask(SecondSubtaskFor1Epic);

        Subtask ThirdSubtaskFor1Epic = new Subtask("Третья подзадача", firstEpic.getId());
        taskManager.createSubtask(ThirdSubtaskFor1Epic);

        Epic SecondEpic = new Epic("Эпик 2", "Описание 2");
        taskManager.createEpicTask(SecondEpic);


        taskManager.getEpicById(1);


        taskManager.getEpicById(SecondEpic.getId());


        taskManager.getEpicById(1);


        taskManager.getSubTaskById(2);


        taskManager.getSubTaskById(3);


        taskManager.getSubTaskById(ThirdSubtaskFor1Epic.getId());


        taskManager.getSubTaskById(3);


        taskManager.removeSubTaskById(2);

        taskManager.removeEpicById(1);
        System.out.println(taskManager.getHistory());


    }
}