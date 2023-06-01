package manager.historyManager;


import tasks.Task;

public class Node {
    public Task data;
    public Node next;
    public Node prev;

    public Node(Task data, Node prev, Node next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }


}
