package manager.historyManager;

import tasks.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    class CustomLinkedList {
        private Node head;
        private Node tail;

        private void linkLast(Task task) {
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

        private ArrayList<Task> getTasks(){
            ArrayList<Task> tasksArray = new ArrayList<>();
            for (Node node : history) {
                tasksArray.add(node.data);
            }
            return tasksArray;
        }
    }
    private final CustomLinkedList customLinkedList = new CustomLinkedList();

    private final List<Node> history = new ArrayList<>();

    private final HashMap<Integer, Node> assistantMap = new HashMap<>();

    @Override
    public void add(Task task) {
        if(assistantMap.containsKey(task.getId())){
            customLinkedList.removeNode(assistantMap.get(task.getId()));
            remove(task.getId());
        }
        customLinkedList.linkLast(task);
        history.add(customLinkedList.tail);
        assistantMap.put(task.getId(), customLinkedList.tail);
    }

    @Override
    public void remove(int id) {
           history.remove(assistantMap.get(id));
    }

    @Override
    public List<Task> getHistory() {
        return customLinkedList.getTasks();
    }

}
