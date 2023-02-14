package manager.historyManager;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> historyOfTasks = new ArrayList<>(10);

    private void adjustHistorySize(List<Task> historyOfTasks){
        if(historyOfTasks.size() == 10){
            historyOfTasks.remove(0);
        }
    }

    @Override
    public void add(Task task) {
        adjustHistorySize(historyOfTasks);
        historyOfTasks.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return historyOfTasks;
    }
}
