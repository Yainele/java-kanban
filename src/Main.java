public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        Epic epic1 =
                new Epic("Переезд","Переезд в другой город", manager.generateID());
        manager.createEpicTask(epic1);

        Subtask subtask1ForEpic1 =
                new Subtask("Собрать коробки", Task.Status.NEW, manager.generateID());
        manager.createSubtask(subtask1ForEpic1);

        Subtask subtask2ForEpic1 =
                new Subtask("Заказать перевозку вещей", Task.Status.NEW, manager.generateID());
        manager.createSubtask(subtask2ForEpic1);

        Epic epic2 =
                new Epic("Приготовить обед","Приготовить блюдо на 4 человека", manager.generateID());
        manager.createEpicTask(epic2);

        Subtask subtask1ForEpic2 =
                new Subtask("Купить овощи", Task.Status.NEW, manager.generateID());
        manager.createSubtask(subtask1ForEpic2);

        Epic epicForReplace =
                new Epic("Помыть машину", "Поехать на мойку", epic1.getId());

        Subtask subtaskForReplace1 =
                new Subtask("Купить машину", Task.Status.NEW, subtask1ForEpic1.getId());


        Subtask subtaskForReplace2 =
                new Subtask("Заморать машину", Task.Status.NEW, subtask2ForEpic1.getId());


        System.out.println(manager.getEpicByID(1) + "\n");
        System.out.println(manager.getEpicByID(4) + "\n");

        System.out.println("Обновление первого эпика: \n");

        manager.updateEpic(epicForReplace);
        manager.updateSubtask(subtaskForReplace1);
        manager.updateSubtask(subtaskForReplace2);

        System.out.println(manager.getEpicByID(1) + "\n");

        manager.getSubTaskByID(2).setStatus(Task.Status.DONE);
        manager.getSubTaskByID(3).setStatus(Task.Status.IN_PROGRESS);

        manager.getSubTaskByID(5).setStatus(Task.Status.IN_PROGRESS);

        System.out.println("Обновление статусов всех задач: \n");

        System.out.println(manager.getEpicByID(1) + "\n");
        System.out.println(manager.getEpicByID(4) + "\n");

        System.out.println("Удаление подзадачи первого эпика: \n");

        manager.removeSubTaskByID(3);
        System.out.println(manager.getEpicByID(1) + "\n");

        System.out.println("Удаление всех подзадач всех эпиков: \n");

        manager.removeAllSubtasks();
        System.out.println(manager.getEpicByID(1) + "\n");
        System.out.println(manager.getEpicByID(4));

    }
}