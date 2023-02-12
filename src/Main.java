import manager.Manager;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        Epic epic1 =
                new Epic("Переезд","Переезд в другой город");
        manager.createEpicTask(epic1);

        Subtask subtask1ForEpic1 =
                new Subtask("Собрать коробки", epic1.getId());
        manager.createSubtask(subtask1ForEpic1);

        Subtask subtask2ForEpic1 =
                new Subtask("Заказать перевозку вещей", epic1.getId());
        manager.createSubtask(subtask2ForEpic1);

        Epic epic2 =
                new Epic("Приготовить обед","Приготовить блюдо на 4 человека");
        manager.createEpicTask(epic2);

        Subtask subtask1ForEpic2 =
                new Subtask("Купить овощи", epic2.getId());
        manager.createSubtask(subtask1ForEpic2);

        Epic epicForReplace =
                new Epic("Помыть машину", "Поехать на мойку");
        epicForReplace.setId(epic1.getId());

        Subtask subtaskForReplace1 =
                new Subtask("Купить машину", epicForReplace.getId());
        subtaskForReplace1.setId(subtask1ForEpic1.getId());


        Subtask subtaskForReplace2 =
                new Subtask("Заморать машину", epicForReplace.getId());
        subtaskForReplace2.setId(subtask2ForEpic1.getId());

        System.out.println(manager.getEpicById(1) + "\n");
        System.out.println(manager.getEpicById(4) + "\n");

        System.out.println("Обновление первого эпика: \n");

        manager.updateEpic(epicForReplace);
        manager.updateSubtask(subtaskForReplace1);
        manager.updateSubtask(subtaskForReplace2);

        System.out.println(manager.getEpicById(1) + "\n");

        manager.getSubTaskById(2).setStatus(Status.DONE);
        manager.getSubTaskById(3).setStatus(Status.IN_PROGRESS);

        manager.getSubTaskById(5).setStatus(Status.IN_PROGRESS);

        System.out.println("Обновление статусов всех задач: \n");

        System.out.println(manager.getEpicById(1) + "\n");
        System.out.println(manager.getEpicById(4) + "\n");

        System.out.println("Удаление подзадачи первого эпика: \n");

        manager.removeSubTaskById(3);
        System.out.println(manager.getEpicById(1) + "\n");

        System.out.println("Удаление всех подзадач всех эпиков: \n");
        manager.removeAllSubtasks();
        System.out.println(manager.getEpicById(1) + "\n");
        System.out.println(manager.getEpicById(4));


    }
}