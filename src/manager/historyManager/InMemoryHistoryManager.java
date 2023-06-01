package manager.historyManager;

import tasks.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final HashMap<Integer, Node> history = new LinkedHashMap<>();
    private Node head;
    private Node tail;

     void linkLast(Task task) {
         final Node oldTail = tail;
         final Node newNode = new Node(task,tail, null);

          tail = newNode;

         if (oldTail == null){
             head = newNode;
         }
         else{
             oldTail.next = newNode;
         }
     }

    private void removeNode(Node node){
        if (node.prev != null){
            node.prev.next = node.next;
        }

        if(node.next != null){
            node.next.prev = node.prev;
        }
    }


    @Override
    public void add(Task task) {
        if(history.containsKey(task.getId())){
            removeNode(history.get(task.getId()));
            remove(task.getId());
        }
        linkLast(task);
        history.put(task.getId(),tail);

    }

    @Override
    public void remove(int id) {
           history.remove(id);
    }

    private ArrayList<Task> getTasks(){
        ArrayList<Task> tasksArray = new ArrayList<>();
        for (Node value : history.values()) {
            tasksArray.add(value.data);
        }
        return tasksArray;
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

}
