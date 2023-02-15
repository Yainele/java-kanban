package manager.historyManager;

import tasks.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> historyOfTasks = new LinkedList<>();

    @Override
    public void add(Task task) {
        adjustHistorySize();
        historyOfTasks.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return historyOfTasks;
    }

    private void adjustHistorySize(){
        if(historyOfTasks.size() == 10){
            historyOfTasks.remove(0);
        }
    }
}
