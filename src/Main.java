import manager.Managers;
import manager.taskManager.TaskManager;
import tasks.Epic;
import tasks.Subtask;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefault();

        Epic firstEpic = new Epic("Написать java-kanban", "Сдача финального проекта 4");
        taskManager.createEpicTask(firstEpic);

        Subtask FirstSubtaskFor1Epic = new Subtask("Прочитать ТЗ", firstEpic.getId());
        taskManager.createSubtask(FirstSubtaskFor1Epic);

        Subtask SecondSubtaskFor1Epic = new Subtask("Выполнить ТЗ", firstEpic.getId());
        taskManager.createSubtask(SecondSubtaskFor1Epic);

        //Проверка вместимости списка истории
        //11
        taskManager.getEpicById(1);
        //10
        taskManager.getSubTaskById(3);
        taskManager.getSubTaskById(2);
        taskManager.getEpicById(1);
        taskManager.getEpicById(1);
        taskManager.getSubTaskById(3);
        taskManager.getSubTaskById(2);
        taskManager.getEpicById(1);
        taskManager.getEpicById(1);
        taskManager.getSubTaskById(3);
        taskManager.getSubTaskById(3);

        System.out.println(taskManager.getHistory());

    }
}